package de.backxtar.commands.manage;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.managers.CommandInterface;

public class HelpCommand implements CommandInterface {
    private final Config.Colors colors = Config.getColors();

    @Override
    public void run(String[] cmdValues, TS3Api api, TextMessageEvent event, Client client) {
        api.sendPrivateMessage(client.getId(), "\n" +
                        "Here are my Commands, [b]" + client.getNickname() + "[/b]:\n" +
                "╰ [color=" + colors.mainColor + "][b]@key yourAPIKey[/b][/color] ⎯  Add your key to me [b]Gw2-Key[/b]\n" +
                "╰ [color=" + colors.mainColor + "][b]@keyremove[/b][/color] ⎯  Removes key from me [b]Gw2-Key[/b]\n" +
                "╰ [color=" + colors.mainColor + "][b]@uid[/b][/color] ⎯  Shows you your [b]UID[/b] \n" +
                "╰ [color=" + colors.mainColor + "][b]@timer 1h,2m name | 11.11.2011-13:30 name[/b][/color] ⎯  Starts a [b]Timer[/b]\n" +
                "╰ [color=" + colors.mainColor + "][b]@timers[/b][/color] ⎯  Shows Timers [b]Timer[/b] \n" +
                "╰ [color=" + colors.mainColor + "][b]@cancel timer[/b][/color] ⎯  Stops a [b]Timer[/b] \n" +
                "╰ [color=" + colors.mainColor + "][b]@me[/b][/color] ⎯  Gives you your [b]GW2 account information[/b] \n" +
                "╰ [color=" + colors.mainColor + "][b]@wallet[/b][/color] ⎯  Shows you your [b]GW2-Wallet[/b] \n" +
                "╰ [color=" + colors.mainColor + "][b]@bosses[/b][/color] ⎯  Shows you your [b]GW2 Weekly Raid Summary[/b] \n" +
                "");
    }
}
