package de.backxtar;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.exception.TS3Exception;
import de.backxtar.systems.AfkMover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TS3Bot
{
    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static Logger logger = LoggerFactory.getLogger(TS3Bot.class);
    public static TS3Bot ts3Bot;
    public TS3Api api;

    public TS3Bot() throws IOException, TS3Exception
    {
        ts3Bot = this;
        final TS3Config config = new TS3Config();
        Properties cfg = new Properties();
        File file = new File("config.cfg");

        if (file.createNewFile())
            logger.info("New config created.");
        else if (!file.createNewFile() && file.length() == 0)
        {
            logger.info("config.cfg is empty!");
            return;
        }
        cfg.load(new FileInputStream("config.cfg"));
        Enumeration<Object> en = cfg.keys();
        String host = "", username = "", password = "", nickname = "";

        while (en.hasMoreElements())
        {
            String key = (String) en.nextElement();

            if (key.equalsIgnoreCase("host"))
                host = (String) cfg.get(key);
            if (key.equalsIgnoreCase("username"))
                username = (String) cfg.get(key);
            if (key.equalsIgnoreCase("password"))
                password = (String) cfg.get(key);
            if (key.equalsIgnoreCase("nickname"))
                nickname = (String) cfg.get(key);
        }
        logger.info("Config loaded.");
        config.setHost(host);
        config.setEnableCommunicationsLogging(true);
        config.setFloodRate(TS3Query.FloodRate.UNLIMITED);

        final TS3Query query = new TS3Query(config);
        query.connect();

        api = query.getApi();
        api.login(username, password);
        api.selectVirtualServerById(1);
        api.setNickname(nickname);

        EventDistributor.loadEvents();
        AfkMover.loadAfkMover();
    }

    public static void main(String[] args)
    {
        try {
            new TS3Bot();
            logger.info("Configuration successful!");
            logger.info("Bot online!");
        } catch (IOException | TS3Exception e) {
            logger.info("Configuration failed!");
            logger.info("Please check config.cfg");
        }
    }
}
