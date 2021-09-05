package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import de.backxtar.Config;
import de.backxtar.DerGeraet;
import de.backxtar.gw2.CallGuild;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GuildInfo {
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);
    private static final TS3Api api = DerGeraet.getInstance().api;

    public static void loadGuildInfo() {
        if (Config.getConfigData().guildChannelID == 0) return;
        Future<CallGuild.GWCallGuild> guildAsync = executor.submit(CallGuild::getOwnGuild);
        Future<List<CallGuild.GWCallGuildMembers>> memberAsync = executor.submit(CallGuild::getMembers);

        CallGuild.GWCallGuild guild = null;
        List<CallGuild.GWCallGuildMembers> memberList = null;
        String desc = api.getChannelInfo(Config.getConfigData().guildChannelID).getDescription();

        try {
            guild = guildAsync.get();
            memberList = memberAsync.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        StringBuilder guildList = new StringBuilder();

        if (!Config.getConfigData().guildRanks[0].equalsIgnoreCase("0") && memberList != null && !memberList.isEmpty()) {
            StringBuilder[] stringBuilders = new StringBuilder[Config.getConfigData().guildRanks.length];

            for (int i = 0; i < stringBuilders.length; i++) {
                stringBuilders[i] = getRanks(memberList, Config.getConfigData().guildRanks[i]);
            }

            for (int i = 0; i < stringBuilders.length; i++) {
                guildList.append("\n\n" + "[size=10][color=orange][b]").append(Config.getConfigData().guildRanks[i])
                        .append("[/b][/color]").append("\n").append(stringBuilders[i]);
            }
        }

        if (guild == null) return;
        String info = getInfos(guild) + guildList + "\n\n[url=https://www.paypal.com/donate/?hosted_button_id=MEW4LZBC24EQQ][img]http://i.epvpimg.com/Tlnqcab.png[/img][/url]";

        if (desc.equalsIgnoreCase(info)) return;
        api.editChannel(Config.getConfigData().guildChannelID, ChannelProperty.CHANNEL_DESCRIPTION, info);
    }

    private static String getInfos(CallGuild.GWCallGuild guild) {
        String memberIcon = "[img]http://i.epvpimg.com/F7l1aab.png[/img]";
        String aetheriumIcon = "[img]http://i.epvpimg.com/FRHBeab.png[/img]";
        String favorIcon = "[img]http://i.epvpimg.com/u8EWdab.png[/img]";
        String influenceIcon = "[img]http://i.epvpimg.com/7iWhbab.png[/img]";
        String resonanceIcon = "[img]http://i.epvpimg.com/uVuAbab.png[/img]";

        return "[center][size=11][color=red][b]" + guild.name + " [" + guild.tag + "] (" + guild.level + ")[/b][/color][/size]" +
                "\n\n[size=10][color=orange][b]Ankündigung:[/b][/color]\n" + guild.motd +
                "\n\n" + memberIcon + " [size=10][color=orange][b]Members:[/b][/color] " + guild.member_count + "/" + guild.member_capacity +
                "\n" + aetheriumIcon + " [size=10][color=orange][b]Ätherium:[/b][/color] " + guild.aetherium +
                "\n" + favorIcon + " [size=10][color=orange][b]Gunst:[/b][/color] " + guild.favor +
                "\n" + influenceIcon + " [size=10][color=orange][b]Einfluss:[/b][/color] " + guild.influence +
                "\n" + resonanceIcon + " [size=10][color=orange][b]Resonanz:[/b][/color] " + guild.resonance + "[/size]";
    }

    private static StringBuilder getRanks(List<CallGuild.GWCallGuildMembers> members, String rank) {
        StringBuilder builder = new StringBuilder();
        int count = 0;

        for (CallGuild.GWCallGuildMembers member : members) {
            if (member.rank.equalsIgnoreCase(rank))
                count++;
        }

        for (CallGuild.GWCallGuildMembers member : members) {
            if (member.rank.equalsIgnoreCase(rank)) {
                count--;
                builder.append(member.name);
                if (count > 0) builder.append("\n");
            }
        }
        if (builder.toString().isEmpty()) builder.append("Keine");
        return builder;
    }
}
