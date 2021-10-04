package de.backxtar.commands.info;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.managers.CommandInterface;
import de.backxtar.gw2.*;

import java.util.concurrent.*;

public class MeCommand implements CommandInterface {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    private final Config.Colors colors = Config.getColors();

    @Override
    public void run(String[] cmdValues, TS3Api api, TextMessageEvent event, Client client) {
        String[] gw2Values = CallToken.isValid(client);
        if (gw2Values == null) return;

        Future<CallAccount.GWCallAccount> accountAsync = executor.submit(() -> CallAccount.getAccount(gw2Values[0], client));
        Future<CallPvP.GWCallPvP> pvpAsync = executor.submit(() -> CallPvP.getPvp(gw2Values[0], client));

        CallAccount.GWCallAccount account;
        CallPvP.GWCallPvP pvp;

        try {
            account = accountAsync.get();
            pvp = pvpAsync.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return;
        }
        if (account == null || pvp == null) return;
        CallWorld.GWCallWorld world = CallWorld.getWorld(account.world, client).get(0);

        if (world == null) return;
        StringBuilder guildBuilder = getBuilder(account, 1);
        StringBuilder accessBuilder = getBuilder(account, 2);

        if (guildBuilder == null || accessBuilder == null) {
            api.sendPrivateMessage(client.getId(), "[color=red]✘[/color] Oops, something's not working!");
            return;
        }
        api.sendPrivateMessage(client.getId(),
                "\n" +
                        "Here is your Account Information!, [b]" + client.getNickname() + "[/b]:\n" +
                        "╰ [color=" + colors.mainColor + "][b]Gw2-Account:[/b][/color] " + account.name + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]Created:[/b][/color] " + getDate(account.created) + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]Playtime:[/b][/color] " + getAge(account.age) + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]Content:[/b][/color] " + accessBuilder + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]Server:[/b][/color] " + world.name + " [" + world.population + "]\n" +
                        "╰ [color=" + colors.mainColor + "][b]Commander:[/b][/color] " + (account.commander ? "Yes" : "No") + "\n" +
                        (account.guilds.length > 0 ? "╰ [color=" + colors.mainColor + "][b]Guilds:[/b][/color] " + guildBuilder + "\n" : "") +
                        "╰ [color=" + colors.mainColor + "][b]Fractal Level:[/b][/color] " + account.fractal_level + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]WvW-Rank:[/b][/color] " + account.wvw_rank + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]PvP-Rank:[/b][/color] " + pvp.pvp_rank + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]PvP-Rank-Points:[/b][/color] " + pvp.pvp_rank_points + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]PvP-Wins:[/b][/color] " + pvp.aggregate.wins + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]PvP-Losses:[/b][/color] " + pvp.aggregate.losses + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]PvP-desertions:[/b][/color] " + pvp.aggregate.desertions + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]Daily-AP:[/b][/color] " + account.daily_ap + "\n" +
                        "╰ [color=" + colors.mainColor + "][b]Monthly-AP:[/b][/color] " + account.monthly_ap);
    }

    private StringBuilder getBuilder(CallAccount.GWCallAccount account, int mode) {
        StringBuilder builder = new StringBuilder();
        CallGuild.GWCallGuild guild;
        int count;

        if (mode == 1) {
            count = account.guilds.length;

            for (int i = 0; i < count; i++) {
                guild = CallGuild.getGuild(account.guilds[i]);
                if (guild == null) return null;

                if (i < (count - 1))
                    builder.append(guild.name).append(" [").append(guild.tag).append("]").append(", ");
                else builder.append(guild.name).append(" [").append(guild.tag).append("]");
            }
        } else if (mode == 2) {
            count = account.access.length;

            for (int i = 0; i < count; i++) {
                if (account.access[i].equalsIgnoreCase("PlayForFree")) {
                    builder.append("Guild Wars 2 [Free]");
                    if (i < (count - 1)) builder.append(", ");
                }
                if (account.access[i].equalsIgnoreCase("GuildWars2")) {
                    builder.append("Guild Wars 2 [Payed]");
                    if (i < (count - 1)) builder.append(", ");
                }
                if (account.access[i].equalsIgnoreCase("HeartOfThorns")) {
                    builder.append("Heart of Thorns [1]");
                    if (i < (count - 1)) builder.append(", ");
                }
                if (account.access[i].equalsIgnoreCase("PathOfFire")) {
                    builder.append("Path of Fire [2]");
                    if (i < (count - 1)) builder.append(", ");
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

        return (days > 0 ? days + " Days, " : "") +
                (hours > 0 ? hours + " Hours, " : "") +
                (minutes > 0 ? minutes + " Minutes" : "");
    }
}
