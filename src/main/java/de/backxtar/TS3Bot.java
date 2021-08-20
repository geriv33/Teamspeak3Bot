package de.backxtar;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.exception.TS3Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class TS3Bot
{
    public static Logger logger = LoggerFactory.getLogger(TS3Bot.class);
    public static TS3Bot ts3Bot;

    //Config
    public final TS3Config config = new TS3Config();
    public final TS3Query query = new TS3Query(config);
    public final TS3Api api = query.getApi();

    public TS3Bot() throws IOException, TS3Exception
    {
        ts3Bot = this;
        
        Properties cfg = new Properties();
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

        config.setHost(host);
        config.setEnableCommunicationsLogging(true);
        config.setFloodRate(TS3Query.FloodRate.DEFAULT);

        query.connect();

        api.login(username, password);
        api.selectVirtualServerById(1);
        api.setNickname(nickname);

        EventDistributor.loadEvents();
    }

    public static void main(String[] args)
    {
        try {
            new TS3Bot();
            logger.info("Bot online!");
        } catch (IOException | TS3Exception e) {
            logger.info("Configuration failed!");
        }
    }
}
