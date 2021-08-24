package de.backxtar.commands;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.CommandInterface;

public class HelpCommand implements CommandInterface {

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        api.sendChannelMessage("Hier ist Deine angeforderte Hilfe, " + client.getNickname() + ":\n\n" +
                "· [color=orange][b]!key yourAPIKey[/b][/color] ⎯  Speichert oder aktualisiert Deinen [b]Gw2-Key[/b]\n" +
                "");
    }
}
