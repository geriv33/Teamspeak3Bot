package de.backxtar;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.exception.TS3Exception;
import de.backxtar.systems.AfkMover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TS3Bot {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    private static final Logger logger = LoggerFactory.getLogger(TS3Bot.class);
    private static TS3Bot ts3Bot;
    public TS3Query query;
    public TS3Api api;

    public TS3Bot() throws IOException, TS3Exception, SQLException, ClassNotFoundException {
        ts3Bot = this;
        final TS3Config config = new TS3Config();
        Config.loadConfig();
        logger.info("config.cfg loaded.");

        config.setHost(Config.getConfigData().ts3Host);
        config.setEnableCommunicationsLogging(true);
        config.setFloodRate(TS3Query.FloodRate.UNLIMITED);

        query = new TS3Query(config);
        query.connect();
        SqlManager.connect();

        api = query.getApi();
        api.login(Config.getConfigData().ts3Username, Config.getConfigData().ts3Password);
        api.selectVirtualServerById(1);
        api.setNickname(Config.getConfigData().ts3Nickname);

        EventManager.loadEvents();
        scheduler.schedule(TS3Bot::initShutdown, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(AfkMover::checkAfk, 1, 5, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(SqlManager::checkConnection, 1, 5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        try {
            new TS3Bot();
            logger.info("Configuration successful.");
            logger.info("Bot online - connected to " + TS3Bot.ts3Bot.api.getServerInfo().getName() + ".");
        } catch (IOException | TS3Exception | SQLException | ClassNotFoundException e) {
            logger.info("Configuration failed!");
            logger.info("Please check config.cfg");
        }
    }

    private static void initShutdown() {
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase("exit")) {
                    if (TS3Bot.ts3Bot.query.isConnected() && !TS3Bot.ts3Bot.scheduler.isShutdown()) {
                        TS3Bot.ts3Bot.scheduler.shutdown();
                        TS3Bot.ts3Bot.query.exit();
                        SqlManager.disconnect();
                        logger.info("Bot offline.");
                        System.exit(0);
                    } else logger.info("Can not shutdown! Please terminate the screen.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TS3Bot getInstance() {
        return ts3Bot;
    }
}
