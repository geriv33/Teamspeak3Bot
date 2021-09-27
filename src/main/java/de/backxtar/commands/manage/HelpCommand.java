package de.backxtar.commands.manage;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.managers.CommandInterface;

public class HelpCommand implements CommandInterface {
    private final Config.Colors colors = Config.getColors();

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        api.sendPrivateMessage(client.getId(), "\n" +
                        "Hier ist Deine angeforderte Hilfe, [b]" + client.getNickname() + "[/b]:\n" +
                "╰ [color=" + colors.mainColor + "][b]!key yourAPIKey[/b][/color] ⎯  Speichert oder aktualisiert Deinen [b]Gw2-Key[/b]\n" +
                "╰ [color=" + colors.mainColor + "][b]!keyremove[/b][/color] ⎯  Löscht Deinen [b]Gw2-Key[/b]\n" +
                "╰ [color=" + colors.mainColor + "][b]!uid[/b][/color] ⎯  Zeigt Dir Deine [b]UID[/b] an\n" +
                "╰ [color=" + colors.mainColor + "][b]!timer 1h,2m | 11.11.2011 13:30[/b][/color] ⎯  Startet einen [b]Timer[/b]\n" +
                "╰ [color=" + colors.mainColor + "][b]!timers[/b][/color] ⎯  Zeigt Dir Deine [b]Timer[/b] an\n" +
                "╰ [color=" + colors.mainColor + "][b]!me[/b][/color] ⎯  Gibt Dir Deine [b]Gw2-Accountinformationen[/b] aus\n" +
                "╰ [color=" + colors.mainColor + "][b]!wallet[/b][/color] ⎯  Zeigt Dir Deine [b]Gw2-Geldbörse[/b] an\n" +
                "╰ [color=" + colors.mainColor + "][b]!bosses[/b][/color] ⎯  Zeigt Dir Deine [b]Gw2-Raid-Überischt[/b] an\n" +
                "");
    }
}
