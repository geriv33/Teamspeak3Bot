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
        public String guildID;
    }

    public static void loadConfig() throws IOException {
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
            if (key.equalsIgnoreCase("guildID"))
                configData.guildID = (String) cfg.get(key);
        }
    }

    public static ConfigData getConfigData() {
        return configData;
    }
}
