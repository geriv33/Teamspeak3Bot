package de.backxtar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static ConfigData configData;
    private static boolean isLive = true;
    private static File file;

    public static class ConfigData {
        public String ts3Host;
        public String ts3Username;
        public String ts3Password;
        public String ts3Nickname = "Der Geraet (Bot)";
        public String dbHost;
        public String dbName;
        public String dbUser;
        public String dbPassword;
        public String prefix = "!";
        public int afkChannelID = 0;
        public int infoChannelID = 0;
        public int welcomeMessage = 0;
        public int tempServerGroupID = 0;
        public String guestServerGroupName;
        public String guildID;
        public String guildLeaderApiKey;
    }

    public static void loadConfig() throws IOException {
        Properties cfg = new Properties();

        if (!isLive)
            file = new File("config.cfg");
        else file = new File("config_test.cfg");

        if (file.createNewFile())
            logger.info("New config created.");
        else if (!file.createNewFile() && file.length() == 0) {
            logger.info("config.cfg is empty!");
            return;
        }
        cfg.load(new FileInputStream(file.getName()));
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
            else if (key.equalsIgnoreCase("ts3Nickname") && cfg.get(key) == null)
                configData.ts3Nickname = "Der Gerät";
            if (key.equalsIgnoreCase("dbHost"))
                configData.dbHost = (String) cfg.get(key);
            if (key.equalsIgnoreCase("dbName"))
                configData.dbName = (String) cfg.get(key);
            if (key.equalsIgnoreCase("dbUser"))
                configData.dbUser = (String) cfg.get(key);
            if (key.equalsIgnoreCase("dbPassword"))
                configData.dbPassword = (String) cfg.get(key);
            if (key.equalsIgnoreCase("prefix"))
                configData.prefix = (String) cfg.get(key);
            if (key.equalsIgnoreCase("afkChannelID")) {
                String value = (String) cfg.get(key);
                configData.afkChannelID = Integer.parseInt(value);
            }
            if (key.equalsIgnoreCase("infoChannelID")) {
                String value = (String) cfg.get(key);
                configData.infoChannelID = Integer.parseInt(value);
            }
            if (key.equalsIgnoreCase("welcomeMessage")) {
                String value = (String) cfg.get(key);
                configData.welcomeMessage = Integer.parseInt(value);
            }
            if (key.equalsIgnoreCase("tempFriendID")) {
                String value = (String) cfg.get(key);
                configData.tempServerGroupID = Integer.parseInt(value);
            }
            if (key.equalsIgnoreCase("guestServerGroupName"))
                configData.guestServerGroupName = (String) cfg.get(key);
            if (key.equalsIgnoreCase("guildID"))
                configData.guildID = (String) cfg.get(key);
            if (key.equalsIgnoreCase("guildLeaderApiKey"))
                configData.guildLeaderApiKey = (String) cfg.get(key);
        }
    }

    public static ConfigData getConfigData() {
        return configData;
    }

    public static File getFile() {
        return file;
    }
}
