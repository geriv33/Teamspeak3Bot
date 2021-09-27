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
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        if (cmdValue.isEmpty()) sendHelp(api, client);
        String[] args = event.getMessage().split(" ");
        if (args.length < 2 || args.length > 3) sendHelp(api, client);
        String dateTime, code, timeStamp = null;

        try {
            if (args.length == 2) {
                dateTime = args[1];
                timeStamp = Timer.calcTimestamp(dateTime);
            } else if (args.length == 3) {
                dateTime = args[1] + " " + args[2];
                timeStamp = Timer.getTimestamp(dateTime);
            }
            code = Timer.generateCode();
            String[] fieldsInsert = {"clientIdentity", "timeStamp", "code"};
            Object[] valuesInsert = {client.getUniqueIdentifier(), timeStamp, code};
            if (timeStamp != null) SqlManager.insert("Timers", fieldsInsert, valuesInsert);
        } catch (NumberFormatException | ParseException | SQLException e) {
            sendHelp(api, client);
            return;
        }
        String[] values = Timer.getValues(timeStamp);
        api.sendPrivateMessage(client.getId(), "[color=green]✔[/color] Dein Timer für den " +
                "[color=" + colors.mainColor + "][b]" + values[0] + "[/b][/color] um " +
                "[color=" + colors.mainColor + "][b]" + values[1] + " Uhr[/b][/color] ist gestellt. " +
                "TimerID: [color=" + colors.mainColor + "][b]" + code + "[/b][/color]");
    }

    @Override
    public void sendHelp(TS3Api api, Client client) {
        api.sendPrivateMessage(client.getId(),
                "[color=red]✘[/color] Bitte benutze [b][color=" + Config.getColors().mainColor + "]" +
                        "!timer 1h,2m[/color][/b] oder [b][color=" + Config.getColors().mainColor + "]" +
                        "!timer 11.11.2011 13:30[/color][/b]!");
    }
}
