package de.backxtar.managers;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public interface CommandInterface {
    default void run(String[] cmdValues, TS3Api api, TextMessageEvent event, Client client) {}
    default void sendHelp(TS3Api api, Client client) {}
}
