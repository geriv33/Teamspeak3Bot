package de.backxtar.commands.timer;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.managers.CommandInterface;
import de.backxtar.managers.SqlManager;
import de.backxtar.systems.Timer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CancelTimerCommand implements CommandInterface {
    private final Config.Colors colors = Config.getColors();

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        if (cmdValue.isEmpty()) sendHelp(api, client);
        String message = event.getMessage();
        String[] init = message.split(" ");
        //!cancel Test
        if (init.length < 2) sendHelp(api, client);
        String[] args = message.split(" ", 2);

        try {
            String[] fields = {"timeStamp", "name"};
            Object[] values = {client.getUniqueIdentifier(), args[1]};
            ResultSet resultSet = SqlManager.select(fields, "Timers", "clientIdentity = ? AND name = ?", values);

            if (!resultSet.next()) {
                api.sendPrivateMessage(client.getId(), "[color=red]✘[/color] Dieser [b][color=red]Timer[/color][/b] " + "existiert nicht!");
                return;
            }
            Timestamp timestamp = resultSet.getTimestamp("timeStamp");
            String name = resultSet.getString("name");
            String[] dateTime = Timer.getValues(timestamp.toString());
            SqlManager.delete("Timers", "clientIdentity = ? AND name = ?", values);

            api.sendPrivateMessage(client.getId(), "[color=green]✔[/color] Dein [color=green][b]Timer (" + name + ")[/b][/color] für den " +
                    "[color=" + colors.mainColor + "][b]" + dateTime[0] + "[/b][/color] um " +
                    "[color=" + colors.mainColor + "][b]" + dateTime[1] + " Uhr[/b][/color] wurde abgebrochen!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendHelp(TS3Api api, Client client) {
        api.sendPrivateMessage(client.getId(),
                "[color=red]✘[/color] Bitte benutze [b][color=" + Config.getColors().mainColor + "]" +
                        "!cancel name[/color][/b]!");
    }
}
