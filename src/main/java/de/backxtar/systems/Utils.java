package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.DerGeraet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class Utils {

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        return sdfDate.format(date);
    }

    public static String getDate(String parseFormat) {
        Instant instant = Instant.parse(parseFormat);
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zdt = instant.atZone(zone);
        Date date = Date.from(zdt.toInstant());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    public static void checkInfo(TS3Api api) {
        String[] infos = api.getChannelInfo(Config.getConfigData().infoChannelID).getName().split(" | ");
        if (infos.length < 2 || !infos[1].equalsIgnoreCase(getDate())) changeInfo();
    }

    public static void changeInfo() {
        TS3Api api = DerGeraet.getInstance().api;
        if (Config.getConfigData().infoChannelID == 0) return;
        int clientSize = 0;

        for (Client client : api.getClients()) {
            if (!client.isServerQueryClient())
                clientSize++;
        }
        String info = "[cspacer0]Clients: " + clientSize + "/" + api.getServerInfo().getMaxClients() + " | " + Utils.getDate();
        if (api.getChannelInfo(Config.getConfigData().infoChannelID).getName().equalsIgnoreCase(info)) return;
        api.editChannel(Config.getConfigData().infoChannelID, ChannelProperty.CHANNEL_NAME, info);
    }
}
