package de.backxtar.commands.key;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.managers.CommandInterface;
import de.backxtar.gw2.CallToken;

public class AddAPIKeyCommand implements CommandInterface {

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        if (cmdValue.isEmpty()) {
            sendHelp(api, client);
            return;
        }
        String[] args = event.getMessage().split(" ");
        CallToken.checkToken(client, args[1]);
    }

    @Override
    public void sendHelp(TS3Api api, Client client) {
        api.sendPrivateMessage(client.getId(),
                "[color=red]✘[/color] Bitte benutze [b][color=" + Config.getColors().mainColor + "]!key yourKey[/color][/b]!");
    }
}
