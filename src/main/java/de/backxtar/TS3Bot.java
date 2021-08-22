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
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TS3Bot {
    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    public static Logger logger = LoggerFactory.getLogger(TS3Bot.class);
    public static TS3Bot ts3Bot;
    public ConfigData configData;
    public TS3Query query;
    public TS3Api api;

    public static class ConfigData {
        public String ts3Host;
        public String ts3Username;
        public String ts3Password;
        public String ts3Nickname = "TS3-Bot";
        public String dbHost;
        public String dbName;
        public String dbUser;
        public String dbPassword;
        public int afkChannelID = -1;
        public int infoChannelID = -1;
        public int welcomeMessage = -1;
    }

    public TS3Bot() throws IOException, TS3Exception, SQLException, ClassNotFoundException {
        ts3Bot = this;
        final TS3Config config = new TS3Config();
        Properties cfg = new Properties();
        File file = new File("config.cfg");

        if (file.createNewFile())
            logger.info("New config created.");
        else if (!file.createNewFile() && file.length() == 0) {
            logger.info("config.cfg is empty!");
            return;
        }
        cfg.load(new FileInputStream("config.cfg"));
        Enumeration<Object> en = cfg.keys();
        configData = new ConfigData();

        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();

            if (key.equalsIgnoreCase("ts3Host"))
                configData.ts3Host = (String) cfg.get(key);
            if (key.equalsIgnoreCase("ts3Username"))
                configData.ts3Username = (String) cfg.get(key);
            if (key.equalsIgnoreCase("ts3Password"))
                configData.ts3Password = (String) cfg.get(key);
            if (key.equalsIgnoreCase("ts3Nickname") && cfg.get(key) != null)
                configData.ts3Nickname = (String) cfg.get(key);
            if (key.equalsIgnoreCase("dbHost"))
                configData.dbHost = (String) cfg.get(key);
            if (key.equalsIgnoreCase("dbName"))
                configData.dbName = (String) cfg.get(key);
            if (key.equalsIgnoreCase("dbUser"))
                configData.dbUser = (String) cfg.get(key);
            if (key.equalsIgnoreCase("dbPassword"))
                configData.dbPassword = (String) cfg.get(key);
            if (key.equalsIgnoreCase("afkChannelID"))
                configData.afkChannelID = (int) cfg.get(key);
            if (key.equalsIgnoreCase("infoChannelID"))
                configData.infoChannelID = (int) cfg.get(key);
            if (key.equalsIgnoreCase("welcomeMessage"))
                configData.welcomeMessage = (int) cfg.get(key);
        }
        logger.info("Config loaded.");
        config.setHost(configData.ts3Host);
        config.setEnableCommunicationsLogging(true);
        config.setFloodRate(TS3Query.FloodRate.UNLIMITED);

        query = new TS3Query(config);
        query.connect();
        SqlManager.connect();

        api = query.getApi();
        api.login(configData.ts3Username, configData.ts3Password);
        api.selectVirtualServerById(1);
        api.setNickname(configData.ts3Nickname);

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
}
