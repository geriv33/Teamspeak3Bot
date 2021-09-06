package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import de.backxtar.Config;
import de.backxtar.DerGeraet;

public class GuildSync {
    private TS3Api api = DerGeraet.getInstance().api;

    public static void syncRights() {
        if (Config.getConfigData().serverGroups == null || Config.getConfigData().guildRanks[0] == "0") return;

    }
}
