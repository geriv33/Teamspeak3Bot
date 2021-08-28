package de.backxtar.managers;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.commands.*;

import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {
    public ConcurrentHashMap<String, CommandInterface> commands;

    public CommandManager() {
        this.commands = new ConcurrentHashMap<>();

        // Command-Register
        this.commands.put("help", new HelpCommand());
        this.commands.put("key", new AddAPIKeyCommand());
        this.commands.put("keyremove", new RemoveAPIKeyCommand());
        this.commands.put("me", new MeCommand());
        this.commands.put("wallet", new WalletCommand());
        this.commands.put("friend", new FriendCommand());
        this.commands.put("ufe", new UfeCommand());
        this.commands.put("arcdps", new ArcDpsCommand());
    }

    public boolean runCmd(String[] command, TS3Api api, TextMessageEvent event, Client client) {
        CommandInterface cmdInterface;

        if ((cmdInterface = this.commands.get(command[0].toLowerCase())) != null) {
            cmdInterface.run(command[1], api, event, client);
            return true;
        }
        return false;
    }
}
