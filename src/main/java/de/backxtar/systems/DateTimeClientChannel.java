package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import de.backxtar.Config;
import de.backxtar.TS3Bot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeClientChannel {

    public static void changeInfo() {
        TS3Api api = TS3Bot.getInstance().api;
        String[] dateTime = getDateTime();
        api.editChannel(Config.getConfigData().infoChannelID, ChannelProperty.CHANNEL_NAME, "Clients: " +
                api.getClients().size() + "/" + api.getServerInfo().getMaxClients() + " | " + dateTime[0] + " | " +
                dateTime[1] + " Uhr");
    }

    public static String[] getDateTime() {
        String[] dateTime = new String[2];
        Date date = new Date();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        dateTime[0] = sdfDate.format(date);
        dateTime[1] = sdfTime.format(date);
        return dateTime;
    }
}
