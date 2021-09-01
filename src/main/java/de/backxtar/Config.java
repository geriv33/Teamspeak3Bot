package de.backxtar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Properties;

public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static ConfigData configData;
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
        public int welcomeMessage = 0;
        public int tradingPostChannelID = 0;
        public int[] afkChannelID;
        public int infoChannelID = 0;
        public int guildChannelID = 0;
        public String[] guildRanks;
        public int arcDpsChannelID = 0;
        public int dailiesChannelID = 0;
        public int tempServerGroupID = 0;
        public String guestServerGroupName;
        public String guildID;
        public String guildLeaderApiKey;
    }

    public static void loadConfig() throws IOException {
        Properties cfg = new Properties();
        boolean isLive = true;

        if (isLive) file = new File("config.cfg");
        else file = new File("config_test.cfg");

        if (file.createNewFile())
            logger.info("New config created.");
        else if (!file.createNewFile() && file.length() == 0) {
            logger.info("config.cfg is empty!");
            return;
        }
        InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file.getName()), "UTF-8");
        cfg.load(streamReader);
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
                configData.ts3Nickname = "Der Geraet (Bot)";
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
                String[] values = value.split(",");
                if (values.length == 0 || values[0].equalsIgnoreCase("0")) {
                    configData.afkChannelID = new int[1];
                    configData.afkChannelID[0] = 0;
                } else {
                    configData.afkChannelID = new int[values.length];
                    for (int i = 0; i < configData.afkChannelID.length; i++) {
                        configData.afkChannelID[i] = Integer.parseInt(values[i]);
                    }
                }
            }
            if (key.equalsIgnoreCase("infoChannelID")) {
                String value = (String) cfg.get(key);
                configData.infoChannelID = Integer.parseInt(value);
            }
            if (key.equalsIgnoreCase("guildChannelID")) {
                String value = (String) cfg.get(key);
                configData.guildChannelID = Integer.parseInt(value);
            }
            if (key.equalsIgnoreCase("guildRanks")) {
                String value = (String) cfg.get(key);
                String[] values = value.split(",");

                if (values.length == 0) {
                    configData.guildRanks = new String[1];
                    configData.guildRanks[0] = "0";
                } else {
                    configData.guildRanks = new String[values.length];
                    for (int i = 0; i < configData.guildRanks.length; i++) {
                        configData.guildRanks[i] = values[i];
                    }
                }
            }
            if (key.equalsIgnoreCase("arcDpsChannelID")) {
                String value = (String) cfg.get(key);
                configData.arcDpsChannelID = Integer.parseInt(value);
            }
            if (key.equalsIgnoreCase("tradingPostChannelID")) {
                String value = (String) cfg.get(key);
                configData.tradingPostChannelID = Integer.parseInt(value);
            }
            if (key.equalsIgnoreCase("dailiesChannelID")) {
                String value = (String) cfg.get(key);
                configData.dailiesChannelID = Integer.parseInt(value);
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
