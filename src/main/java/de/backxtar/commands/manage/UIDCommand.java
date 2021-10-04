package de.backxtar.commands.manage;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.managers.CommandInterface;

public class UIDCommand implements CommandInterface {

    @Override
    public void run(String[] cmdValues, TS3Api api, TextMessageEvent event, Client client) {
        api.sendPrivateMessage(client.getId(), "[b]" + client.getNickname() + "[/b], Your [b]UID[/b] is: " +
                "[color=" + Config.getColors().mainColor + "][b]" + client.getUniqueIdentifier() + "[/b][/color]\n" +
                "The [b]UID[/b] is required for the forum.");
    }
}
