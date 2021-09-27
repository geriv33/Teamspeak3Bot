package de.backxtar.commands.timer;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.managers.CommandInterface;
import de.backxtar.managers.SqlManager;
import de.backxtar.systems.Timer;

import java.sql.SQLException;
import java.text.ParseException;

public class StartTimerCommand implements CommandInterface {
    private static final Config.Colors colors = Config.getColors();

    @Override
    public void run(String[] cmdValues, TS3Api api, TextMessageEvent event, Client client) {
        if (cmdValues.length < 3) {
            sendHelp(api, client);
            return;
        }
        String[] args = event.getMessage().split(" ", 3);
        String[] timeFormat = args[1].split("-");
        String dateTime, name, timeStamp;

        try {
            if (timeFormat.length < 2) {
                dateTime = timeFormat[0];
                name = args[2];
                timeStamp = Timer.calcTimestamp(dateTime);
            } else {
                dateTime = timeFormat[0] + " " + timeFormat[1];
                name = args[2];
                timeStamp = Timer.getTimestamp(dateTime);
            }
            String[] fieldsInsert = {"clientIdentity", "timeStamp", "name"};
            Object[] valuesInsert = {client.getUniqueIdentifier(), timeStamp, name};
            SqlManager.insert("Timers", fieldsInsert, valuesInsert);
        } catch (NumberFormatException | ParseException | SQLException e) {
            sendHelp(api, client);
            return;
        }
        String[] values = Timer.getValues(timeStamp);
        api.sendPrivateMessage(client.getId(), "[color=green]✔[/color] Dein [color=green][b]Timer " +
                "(" + name + ")[/b][/color] für den [color=" + colors.mainColor + "][b]" + values[0] + "[/b][/color] um " +
                "[color=" + colors.mainColor + "][b]" + values[1] + " Uhr[/b][/color] ist gestellt. " +
                "Timer: [color=" + colors.mainColor + "][b]" + name + "[/b][/color]");
    }

    @Override
    public void sendHelp(TS3Api api, Client client) {
        api.sendPrivateMessage(client.getId(),
                "[color=red]✘[/color] Bitte benutze [b][color=" + Config.getColors().mainColor + "]" +
                        "!timer 1h,2m name[/color][/b] oder [b][color=" + Config.getColors().mainColor + "]" +
                        "!timer 11.11.2011-13:30 name[/color][/b]!");
    }
}
