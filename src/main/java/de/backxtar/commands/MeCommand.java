package de.backxtar.commands;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.CommandInterface;
import de.backxtar.gw2.CallAccount;
import de.backxtar.gw2.CallGuild;
import de.backxtar.gw2.CallToken;
import de.backxtar.gw2.CallWorld;

import java.util.ArrayList;
import java.util.List;

public class MeCommand implements CommandInterface {

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        String[] gw2Values = CallToken.isValid(client);
        if (gw2Values == null) return;

        CallAccount.GWCallAccount account   = CallAccount.getAccount(gw2Values[0]);
        CallWorld.GWCallWorld world         = CallWorld.getWorld(account.world).get(0);

        StringBuilder guildBuilder = getBuilder(account, 1);
        StringBuilder guildLeadBuilder = getBuilder(account, 2);
        StringBuilder accessBuilder = getBuilder(account, 3);
        String population = world.population;

        api.sendPrivateMessage(client.getId(),
                "Hier sind Deine Account-Informationen, [b]" + client.getNickname() + "[/b]:\n\n" +
                        "[color=orange][b]Gw2-Account:[/b][/color] " + account.name + "\n" +
                        "[color=orange][b]Erstellt:[/b][/color] " + getDate(account.created) + "\n" +
                        "[color=orange][b]Alter:[/b][/color] " + getAge(account.age) + "\n" +
                        "[color=orange][b]Inhalte:[/b][/color] " + accessBuilder + "\n" +
                        "[color=orange][b]Server:[/b][/color] " + world.name + " [" + population + "]\n" +
                        "[color=orange][b]Kommandeur:[/b][/color] " + (account.commander ? "Ja" : "Nein") + "\n" +
                        (account.guilds.length > 0 ? "[color=orange][b]Gilden:[/b][/color] " + guildBuilder + "\n" : "") +
                        (account.leader.length > 0 ? "[color=orange][b]Leader:[/b][/color] " + guildLeadBuilder + "\n" : "") +
                        "[color=orange][b]Fraktal Level:[/b][/color] " + account.fractal_level + "\n" +
                        "[color=orange][b]WvW-Rang:[/b][/color] " + account.wvw_rank + "\n" +
                        "[color=orange][b]Tägliche-AP:[/b][/color] " + account.daily_ap + "\n" +
                        "[color=orange][b]Monatliche-AP:[/b][/color] " + account.monthly_ap);
    }

    private StringBuilder getBuilder(CallAccount.GWCallAccount account, int mode) {
        StringBuilder builder = new StringBuilder();
        CallGuild.GWCallGuild guild;
        int count;

        if (mode == 1) {
            count = account.guilds.length;

            for (int i = 0; i < count; i++) {
                guild = CallGuild.getGuild(account.guilds[i]);

                if (i < (count - 1))
                    builder.append(guild.name + " [" + guild.tag + "]").append("\n");
                else builder.append(guild.name + " [" + guild.tag + "]");
            }
        } else if (mode == 2) {
            count = account.leader.length;

            for (int i = 0; i < count; i++) {
                guild = CallGuild.getGuild(account.leader[i]);

                if (i < (count -1))
                    builder.append(guild.name + " [" + guild.tag + "]").append("\n");
                else builder.append(guild.name + " [" + guild.tag + "]");
            }
        } else if (mode == 3) {
            count = account.access.length;

            for (int i = 0; i < count; i++) {
                if (account.access[i].equalsIgnoreCase("PlayForFree")) {
                    builder.append("Guild Wars 2 [Free]");
                    if (i < (count -1)) builder.append("\n");
                }
                if (account.access[i].equalsIgnoreCase("GuildWars2")) {
                    builder.append("Guild Wars 2 [Payed]");
                    if (i < (count -1)) builder.append("\n");
                }
                if (account.access[i].equalsIgnoreCase("HeartOfThorns")) {
                    builder.append("Heart of Thorns [1]");
                    if (i < (count -1)) builder.append("\n");
                }
                if (account.access[i].equalsIgnoreCase("PathOfFire")) {
                    builder.append("Path of Fire [2]");
                    if (i < (count -1)) builder.append("\n");
                }
            }
        }
        return builder;
    }

    private String getDate(String created) {
        String[] args = created.substring(0, created.indexOf("T")).split("-");
        String day = args[2];
        String month = args[1];
        String year = args[0];

        return day + "." + month + "." + year;
    }

    private String getAge(long age) {
        long minutes = age / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        minutes %= 60;
        hours %= 24;

        return (days > 0 ? days + " Tage, " : "") +
                (hours > 0 ? hours + " Stunden, " : "") +
                (minutes > 0 ? minutes + " Minuten" : "");
    }
}
