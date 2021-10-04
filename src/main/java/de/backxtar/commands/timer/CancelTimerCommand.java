package de.backxtar.commands.timer;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.managers.CommandInterface;
import de.backxtar.managers.CommandManager;
import de.backxtar.managers.SqlManager;
import de.backxtar.systems.Timer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CancelTimerCommand implements CommandInterface {
    private final Config.Colors colors = Config.getColors();

    @Override
    public void run(String[] cmdValues, TS3Api api, TextMessageEvent event, Client client) {
        if (cmdValues.length < 2) {
            sendHelp(api, client);
            return;
        }
        //@cancel Test
        String[] args = CommandManager.splitArgs(event.getMessage(), 2);

        try {
            String[] fields = {"timeStamp", "name"};
            Object[] values = {client.getUniqueIdentifier(), args[1]};
            ResultSet resultSet = SqlManager.select(fields, "Timers", "clientIdentity = ? AND name = ?", values);

            if (!resultSet.next()) {
                api.sendPrivateMessage(client.getId(), "[color=red]✘[/color] This [b][color=red]Timer[/color][/b] " + "Does not exsist!");
                return;
            }
            Timestamp timestamp = resultSet.getTimestamp("timeStamp");
            String name = resultSet.getString("name");
            String[] dateTime = Timer.getValues(timestamp.toString());
            SqlManager.delete("Timers", "clientIdentity = ? AND name = ?", values);

            api.sendPrivateMessage(client.getId(), "[color=green]✔[/color] Your [color=green][b]Timer (" + name + ")[/b][/color] for the " +
                    "[color=" + colors.mainColor + "][b]" + dateTime[0] + "[/b][/color] around " +
                    "[color=" + colors.mainColor + "][b]" + dateTime[1] + " Uhr[/b][/color] has been canceled!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendHelp(TS3Api api, Client client) {
        api.sendPrivateMessage(client.getId(),
                "[color=red]✘[/color] Please use [b][color=" + Config.getColors().mainColor + "]" +
                        "@cancel name[/color][/b]!");
    }
}
