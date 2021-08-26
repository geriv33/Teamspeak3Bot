package de.backxtar.commands;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.CommandInterface;
import de.backxtar.Config;

public class FriendCommand implements CommandInterface {

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        String friend = Config.getConfigData().tempServerGroup;
        int guest = api.getServerInfo().getDefaultServerGroup();
        boolean isGuest = false;

        for (int serverGroup : client.getServerGroups()) {
            if (serverGroup == guest) {
                isGuest = true;
                break;
            }
        }
        if (!isGuest || friend.isEmpty()) return;
        api.addServerGroup(friend);
        api.sendPrivateMessage(client.getId(),
                "Dir wurde die Server Gruppe: [color=orange][b]" + friend + "[/b][/color] zugewiesen.");
    }
}
