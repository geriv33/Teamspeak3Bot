package de.backxtar.commands;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.managers.CommandInterface;
import de.backxtar.gw2.CallToken;
import de.backxtar.gw2.CallWallet;
import de.backxtar.gw2.Gw2Utils;

import java.util.List;

public class UfeCommand implements CommandInterface {

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        String[] gw2Values = CallToken.isValid(client);
        if (gw2Values == null) return;

        List<CallWallet.GWCallWallet> wallet = CallWallet.getWallet(gw2Values[0], client);

        if (wallet == null) return;
        String ufe = "";

        for (CallWallet.GWCallWallet currency : wallet) {
            if (currency.id == 59) {
                ufe = Gw2Utils.currency(currency.id, currency.value);
                break;
            }
        }
        if (ufe.isEmpty()) ufe = Gw2Utils.currency(59, 0);

        api.sendPrivateMessage(client.getId(),
                "\n[color=orange][b]Gw2-Account:[/b][/color] " + gw2Values[1] + "\n" +
                        ufe);
    }
}
