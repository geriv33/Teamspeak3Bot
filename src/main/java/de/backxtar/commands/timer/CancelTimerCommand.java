package de.backxtar.commands.timer;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.managers.CommandInterface;

public class CancelTimerCommand implements CommandInterface {

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {

    }

    @Override
    public void sendHelp(TS3Api api, Client client) {

    }
}
