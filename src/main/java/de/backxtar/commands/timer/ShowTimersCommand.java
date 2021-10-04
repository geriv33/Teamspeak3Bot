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

public class ShowTimersCommand implements CommandInterface {
    private static final Config.Colors colors = Config.getColors();

    @Override
    public void run(String[] cmdValues, TS3Api api, TextMessageEvent event, Client client) {
        StringBuilder builder = new StringBuilder();
        int count = 0;

        try {
            Object[] valuesSelect = {client.getUniqueIdentifier()};
            ResultSet resultSet = SqlManager.selectAll("Timers", "clientIdentity = ?", valuesSelect);

            while (resultSet.next()) {
                Timestamp timestamp = resultSet.getTimestamp("timeStamp");
                String name = resultSet.getString("name");
                String[] dateTime = Timer.getValues(timestamp.toString());
                builder.append(++count).append(". [b][color=" + colors.mainColor + "]").append(dateTime[0]).append(" ").append(dateTime[1]).append(" Uhr[/color][/b]")
                        .append(" - [color=" + colors.secondColor + "][b]").append(name).append("[/b][/color]").append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count == 0) {
            api.sendPrivateMessage(client.getId(), "No active timers found!");
            return;
        }
        api.sendPrivateMessage(client.getId(), "[b]" + client.getNickname() + "[/b], " +
                "here are your timers:\n" + builder);
    }
}
