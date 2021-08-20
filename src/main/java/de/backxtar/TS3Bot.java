package de.backxtar;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.exception.TS3Exception;
import de.backxtar.systems.AfkMover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TS3Bot {
    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    public static Logger logger = LoggerFactory.getLogger(TS3Bot.class);
    public static TS3Bot ts3Bot;
    public ConfigData configData;
    public TS3Query query;
    public TS3Api api;

    public static class ConfigData {
        public String host;
        public String username;
        public String password;
        public String nickname;
        public int afkChannelID = 0;
        public int infoChannelID = 0;
        public int welcomeMessage = 0;
    }

    public TS3Bot() throws IOException, TS3Exception {
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

            if (key.equalsIgnoreCase("host"))
                configData.host = (String) cfg.get(key);
            if (key.equalsIgnoreCase("username"))
                configData.username = (String) cfg.get(key);
            if (key.equalsIgnoreCase("password"))
                configData.password = (String) cfg.get(key);
            if (key.equalsIgnoreCase("nickname"))
                configData.nickname = (String) cfg.get(key);
            if (key.equalsIgnoreCase("afkChannelID"))
                configData.afkChannelID = (int) cfg.get(key);
            if (key.equalsIgnoreCase("infoChannelID"))
                configData.infoChannelID = (int) cfg.get(key);
            if (key.equalsIgnoreCase("welcomeMessage"))
                configData.welcomeMessage = (int) cfg.get(key);
        }
        logger.info("Config loaded.");
        config.setHost(configData.host);
        config.setEnableCommunicationsLogging(true);
        config.setFloodRate(TS3Query.FloodRate.UNLIMITED);

        query = new TS3Query(config);
        query.connect();
        api = query.getApi();
        api.login(configData.username, configData.password);
        api.selectVirtualServerById(1);
        api.setNickname(configData.nickname);

        EventDistributor.loadEvents();
        scheduler.schedule(TS3Bot::initShutdown, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(AfkMover::checkAfk, 1, 5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        try {
            new TS3Bot();
            logger.info("Configuration successful.");
            logger.info("Bot online - connected to " + TS3Bot.ts3Bot.api.getServerInfo().getName() + ".");
        } catch (IOException | TS3Exception e) {
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
