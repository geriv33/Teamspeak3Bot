package de.backxtar.events;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.DerGeraet;
import de.backxtar.managers.SqlManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OnClientJoin {
    private static final TS3Api api = DerGeraet.getInstance().api;
    private static final Config.Colors colors = Config.getColors();

    public static void sendWelcome(Client client) {
        api.sendPrivateMessage(client.getId(),
                " Welcome to [b][color=" + colors.mainColor + "]" + api.getServerInfo().getName() + "[color=red][/b], [b]" + client.getNickname() + "[/b]!\n" +
                        "[color=red][b]Note:[/b][/color]  Commands can be executed in [b]private message with the @[/b] type @help  [b]Channel Nachricht[/b] to see what i can do for you!!");
    try {
        String[] fieldsSelect = {"GW2_Key"};
        Object[] valuesSelect = {client.getUniqueIdentifier()};
        ResultSet resultSet = SqlManager.select(fieldsSelect, "API_Keys", "clientIdentity = ?", valuesSelect);
        if (resultSet.next()) return;
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
}