package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.DerGeraet;
import de.backxtar.gw2.CallAccount;
import de.backxtar.gw2.CallGuild;
import de.backxtar.gw2.CallToken;
import de.backxtar.managers.SqlManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class GuildSync {
    private static final TS3Api api = DerGeraet.getInstance().api;

    public static void syncRights() {
        if (Config.getConfigData().serverGroups == null
                || Config.getConfigData().guildRanks[0].equalsIgnoreCase("0"))
            return;

        List<CallGuild.GWCallGuildMembers> members = CallGuild.getMembers();
        CallAccount.GWCallAccount account;

        try {
            ResultSet resultSet = SqlManager.selectAll("API_Keys");

            while (resultSet.next()) {
                String identity = resultSet.getString("clientIdentity");
                String key = resultSet.getString("GW2_Key");
                String accountName = resultSet.getString("accountName");
                Client client = api.getClientByUId(identity);

                if (!CallToken.tokenIsValid(client)) continue;
                account = CallAccount.getAccount(key);

                if (!api.isClientOnline(identity) || Arrays.stream(account.guilds)
                        .noneMatch(id -> id.equalsIgnoreCase(Config.getConfigData().guildID)) ||
                        api.getClientByUId(identity).isServerQueryClient())
                    continue;

                members.parallelStream().forEach(member -> {
                    if (member.name.equalsIgnoreCase(accountName) && !member.rank.equalsIgnoreCase("invited")) {
                        String rank = member.rank;

                        if (Config.getConfigData().serverGroups.containsKey(rank)) {
                            int serverGroupId = Config.getConfigData().serverGroups.get(rank);
                            int[] groups = client.getServerGroups();
                            boolean hasGroup = false;

                            for (int group : groups) {
                                if (group != serverGroupId && !Config.getConfigData().ignoreGroups.contains(group))
                                    api.removeClientFromServerGroup(group, client.getDatabaseId());
                                if (group == serverGroupId) hasGroup = true;
                            }
                            if (!hasGroup) api.addClientToServerGroup(serverGroupId, client.getDatabaseId());
                        }
                    }
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
