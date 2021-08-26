package de.backxtar.events;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroup;
import de.backxtar.Config;
import de.backxtar.SqlManager;
import de.backxtar.systems.DateTimeClientChannel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class OnClientJoin {
    public static void changeInfo(TS3Api api) {
        if (Config.getConfigData().infoChannelID == -1) return;
        String[] dateTime = DateTimeClientChannel.getDateTime();
        api.editChannel(Config.getConfigData().infoChannelID, ChannelProperty.CHANNEL_NAME, "Clients: " +
                api.getClients().size() + "/" + api.getServerInfo().getMaxClients() + " | " + dateTime[0] + " | " +
                dateTime[1] + " Uhr");
    }

    public static void sendWelcome(TS3Api api, ClientJoinEvent e) {
        Client client = api.getClientInfo(e.getClientId());
        api.sendPrivateMessage(client.getId(),
                "Willkommen auf [b][color=orange]" + api.getServerInfo().getName() + "[color=red][/b], [b]" + client.getNickname() + "[b]!");
    }

    public static void gw2ApiReminder(TS3Api api, ClientJoinEvent e) {
        Client client = api.getClientInfo(e.getClientId());
        int guest = api.getServerInfo().getDefaultServerGroup();
        boolean isGuest = false;

        for (int serverGroup : client.getServerGroups()) {
            if (serverGroup == guest) {
                isGuest = true;
                break;
            }
        }

        try {
            String[] fieldsSelect = {"GW2_Key"};
            Object[] valuesSelect = {client.getUniqueIdentifier()};
            ResultSet resultSet = SqlManager.select(fieldsSelect, "API_Keys", "clientIdentity = ?", valuesSelect);
            if (isGuest || !resultSet.next()) return;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        api.sendPrivateMessage(client.getId(), "[color=red]✘[/color] Du hast noch keinen [b][color=red]Gw2-Key[/color][b] hinterlegt\n" +
                "Bitte denke daran einen [b][color=red]Gw2-Key[/color][b] zu hinterlegen. Du kannst hier einen [b][color=red]Gw2-Key[/color][b] erstellen:\n" +
                "https://account.arena.net/applications \n\n" +
                "Bist du ein Freund der Gilde? Gebe [color=orange][b]!friend[/b][/color] ein!");
    }
}
