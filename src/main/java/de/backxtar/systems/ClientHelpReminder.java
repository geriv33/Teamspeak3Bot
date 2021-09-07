package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroupClient;
import de.backxtar.Config;
import de.backxtar.DerGeraet;

import java.util.ArrayList;
import java.util.List;

public class ClientHelpReminder {
    private static final TS3Api api = DerGeraet.getInstance().api;

    public static void doSupport(ClientMovedEvent e) {
        Client client = api.getClientInfo(e.getClientId());
        ChannelInfo channelInfo = api.getChannelInfo(client.getChannelId());
        if (!Config.getConfigData().supportChannels.contains(client.getChannelId())) return;
        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < Config.getConfigData().supportGroups.size(); i++) {
            List<ServerGroupClient> sClients = api.getServerGroupClients(Config.getConfigData().supportGroups.get(i));
            for (ServerGroupClient sClient : sClients) {
                Client supporter = api.getClientByUId(sClient.getUniqueIdentifier());
                if (api.isClientOnline(supporter.getUniqueIdentifier()) && !clients.contains(supporter))
                    clients.add(supporter);
            }
        }
        String sendHelp = clients.size() > 0 ? "Momentan sind [color=orange][b]" + clients.size() + " " +
                        "Supporter[/b][/color] online! Es ist gleich jemand für Dich da!" :
                        "Momentan ist leider [color=orange][b]kein Supporter[/b][/color] online. " +
                        "Bitte komme zu einem späteren Zeitpunkt wieder!";
        api.sendPrivateMessage(client.getId(), sendHelp);

        clients.parallelStream().forEach(supporter -> api.sendPrivateMessage(supporter.getId(),
                "[color=orange][b]" + client.getNickname() + "[/b][/color] " +
                "wartet in [color=orange][b]" + channelInfo.getName() + "[/b][/color] auf Hilfe!"));
    }
}
