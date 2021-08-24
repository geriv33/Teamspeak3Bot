package de.backxtar.commands;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.CommandInterface;
import de.backxtar.gw2.CallAccount;
import de.backxtar.gw2.CallGuild;
import de.backxtar.gw2.CallToken;
import de.backxtar.gw2.CallWorld;

public class MeCommand implements CommandInterface {

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        String[] gw2Values = CallToken.isValid(client);
        if (gw2Values == null) return;

        CallAccount.GWCallAccount account   = CallAccount.getAccount(gw2Values[0]);
        CallWorld.GWCallWorld world         = CallWorld.getWorld(account.world).get(0);
        CallGuild.GWCallGuild guild;
    }
}
