package de.backxtar.commands;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.managers.CommandInterface;

public class HelpCommand implements CommandInterface {

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        api.sendPrivateMessage(client.getId(), "\n" +
                        "Hier ist Deine angeforderte Hilfe, [b]" + client.getNickname() + "[/b]:\n" +
                "╰ [color=" + Config.getColors().mainColor + "][b]!key yourAPIKey[/b][/color] ⎯  Speichert oder aktualisiert Deinen [b]Gw2-Key[/b]\n" +
                "╰ [color=" + Config.getColors().mainColor + "][b]!keyremove[/b][/color] ⎯  Löscht Deinen [b]Gw2-Key[/b]\n" +
                "╰ [color=" + Config.getColors().mainColor + "][b]!friend[/b][/color] ⎯  Gibt Gästen eine temp [b]Friend-Servergruppe[/b]\n" +
                "╰ [color=" + Config.getColors().mainColor + "][b]!me[/b][/color] ⎯  Gibt Dir Deine [b]Gw2-Accountinformationen[/b] aus\n" +
                "╰ [color=" + Config.getColors().mainColor + "][b]!wallet[/b][/color] ⎯  Zeigt Dir Deine [b]Gw2-Geldbörse[/b] an\n" +
                "╰ [color=" + Config.getColors().mainColor + "][b]!bosses[/b][/color] ⎯  Zeigt Dir Deine [b]Gw2-Raid-Überischt[/b] an\n" +
                "");
    }
}
