package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import de.backxtar.Config;
import de.backxtar.DerGeraet;
import de.backxtar.gw2.CallGuild;
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

        try {
            guild = guildAsync.get();
            memberList = memberAsync.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        StringBuilder founders = null;
        StringBuilder leaders = null;
        StringBuilder konkubs = null;
        StringBuilder officers = null;
        StringBuilder members = null;
        StringBuilder holliday = null;
        StringBuilder trail = null;

        if (Config.getConfigData().guildID.equalsIgnoreCase("37BCE50B-899C-E611-80D3-E83935B5B448")) {
            founders = getRanks(memberList, "Gründer");
            leaders = getRanks(memberList, "Leader");
            konkubs = getRanks(memberList, "Konkubinchen");
            officers = getRanks(memberList, "Offizier");
            members = getRanks(memberList, "Member");
            holliday = getRanks(memberList, "Urlauber");
            trail = getRanks(memberList, "Probe");
        }

        String info = getInfos(guild);
        String auraOnly = founders == null ? "" : "\n\n" + "[size=10][color=orange][b]Gründer:[/b][/color]" + "\n" + founders
                + "\n\n" + "[size=10][color=orange][b]Leader:[/b][/color]" + "\n" + leaders
                + "\n\n" + "[size=10][color=orange][b]Konkubinchen:[/b][/color]" + "\n" + konkubs
                + "\n\n" + "[size=10][color=orange][b]Offiziere:[/b][/color]" + "\n" + officers
                + "\n\n" + "[size=10][color=orange][b]Member:[/b][/color]" + "\n" + members
                + "\n\n" + "[size=10][color=orange][b]Urlauber:[/b][/color]" + "\n" + holliday
                + "\n\n" + "[size=10][color=orange][b]Probe:[/b][/color]" + "\n" + trail;
        if (api.getChannelInfo(Config.getConfigData().guildChannelID)
                .getDescription().equalsIgnoreCase(info)) return;
        api.editChannel(Config.getConfigData().guildChannelID, ChannelProperty.CHANNEL_DESCRIPTION, info + auraOnly
                + "\n\n[url=https://www.paypal.com/donate/?hosted_button_id=MEW4LZBC24EQQ][img]http://i.epvpimg.com/Tlnqcab.png[/img][/url]");
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
