package de.backxtar.commands;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.CommandInterface;
import de.backxtar.gw2.CallToken;
import de.backxtar.gw2.CallWallet;
import java.util.List;

public class WalletCommand implements CommandInterface {

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        String[] gw2Values = CallToken.isValid(client);
        if (gw2Values == null) return;

        List<CallWallet.GWCallWallet> wallet = CallWallet.getWallet(gw2Values[0]);
        StringBuilder currencies = new StringBuilder();

        for (int i = 0; i < wallet.size(); i++) {
            currencies.append(currency(wallet.get(i).id, wallet.get(i).value));
            if (i < (wallet.size() -1)) currencies.append("\n");
        }

        api.sendPrivateMessage(client.getId(),
                "Hier ist deine Geldbörse, [b]" + client.getNickname() + "[/b]\n\n" +
                "[color=orange][b]Gw2-Account:[/b][/color] " + gw2Values[1] + "\n" + currencies);
    }

    private String currency(int id, long amount) {
        //https://api.guildwars2.com/v2/currencies?ids=all
        String currency = "";

        switch (id) {
            case 1 : long[] coins = getCoins(amount);
                currency = "[color=orange][b]Coins:[/b][/color] " + (coins[2] > 0 ? "[b]" + coins[2] + "[/b] Gold, " : "") +
                        (coins[1] > 0 ? "[b]" + coins[1] + "[/b] Silber, " : "") +
                        (coins[0] > 0 ? "[b]" + coins[0] + "[/b] Kupfer, " : "");
                break;
            case 2 : currency = "[color=orange][b]Karma:[/b][/color] " + amount;
                break;
            case 3 : currency = "[color=orange][b]Lorbeeren:[/b][/color] " + amount;
                break;
            case 4 :
                //TODO keep on
                break;
        }
        return currency;
    }

    private long[] getCoins(long amount) {
        long[] coins = new long[3];

        long copper = amount;
        long silver = copper / 100;
        long gold   = silver / 100;
        copper %= 100;
        silver %= 100;

        coins[0] = copper;
        coins[1] = silver;
        coins[2] = gold;
        return coins;
    }
}
