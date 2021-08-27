package de.backxtar.commands;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.managers.CommandInterface;
import de.backxtar.managers.SqlManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveAPIKeyCommand implements CommandInterface {

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        String output = "";

        try {
            String[] fieldsSelect = {"GW2_Key"};
            Object[] valuesSelect = {client.getUniqueIdentifier()};
            ResultSet resultSet = SqlManager.select(fieldsSelect, "API_Keys", "clientIdentity = ?", valuesSelect);

            if (resultSet.next()) {
                Object[] valuesDelete = {client.getUniqueIdentifier()};
                SqlManager.delete("API_Keys", "clientIdentity = ?", valuesDelete);
                output = "\n[color=green]✔[/color] Dein [b][color=green]Gw2-Key[/color][b] wurde entfernt.";
            } else output = "\n[color=red]✘[/color] Kein [b][color=green]Gw2-Key[/color][b] vorhanden.";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        api.sendPrivateMessage(client.getId(), output);
    }
}
