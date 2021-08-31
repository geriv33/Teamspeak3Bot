package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import de.backxtar.Config;
import de.backxtar.DerGeraet;
import de.backxtar.gw2.CallGuild;

public class GuildInfo {
    private static final TS3Api api = DerGeraet.getInstance().api;

    public static void loadGuildInfo() {
        if (Config.getConfigData().guildChannelID == 0) return;
        CallGuild.GWCallGuild guild = CallGuild.getOwnGuild();

        String memberIcon = "[img]http://i.epvpimg.com/F7l1aab.png[/img]";
        String aetheriumIcon = "[img]http://i.epvpimg.com/FRHBeab.png[/img]";
        String favorIcon = "[img]http://i.epvpimg.com/u8EWdab.png[/img]";
        String influenceIcon = "[img]http://i.epvpimg.com/7iWhbab.png[/img]";
        String resonanceIcon = "[img]http://i.epvpimg.com/uVuAbab.png[/img]";

        String announcement = "[center][size=11][color=red][b]" + guild.name + " [" + guild.tag + "] (" + guild.level + ")[/b][/color][/size]" +
                "\n\n[size=10][color=orange][b]Ankündigung:[/b][/color][/size]\n" + guild.motd +
                "\n\n" + memberIcon + " [size=10][color=orange][b]Members:[/b][/color][/size] [size=9]" + guild.member_count + "/" + guild.member_capacity + "[/size]" +
                "\n" + aetheriumIcon + " [size=10][color=orange][b]Ätherium:[/b][/color][/size] [size=9]" + guild.aetherium + "[/size]" +
                "\n" + favorIcon + " [size=10][color=orange][b]Gunst:[/b][/color][/size] [size=9]" + guild.favor + "[/size]" +
                "\n" + influenceIcon + " [size=10][color=orange][b]Einfluss:[/b][/color][/size] [size=9]" + guild.influence + "[/size]" +
                "\n" + resonanceIcon + " [size=10][color=orange][b]Resonanz:[/b][/color][/size] [size=9]" + guild.resonance + "[/size]" +
                "\n\n[url=https://www.paypal.com/donate/?hosted_button_id=MEW4LZBC24EQQ][img]http://i.epvpimg.com/Tlnqcab.png[/img][/url]";
        if (api.getChannelInfo(Config.getConfigData().guildChannelID)
                .getDescription().equalsIgnoreCase(announcement)) return;
        api.editChannel(Config.getConfigData().guildChannelID, ChannelProperty.CHANNEL_DESCRIPTION, announcement);
    }
}
