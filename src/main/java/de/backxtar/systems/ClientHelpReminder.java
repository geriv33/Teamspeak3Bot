package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroupClient;
import de.backxtar.Config;
import de.backxtar.DerGeraet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientHelpReminder {
    private static final TS3Api api = DerGeraet.getInstance().api;

    public static void doSupport(ClientMovedEvent e, Client client) {
        if (!Config.getConfigData().supportChannels.contains(e.getTargetChannelId())) return;

        for (int serverGroup : client.getServerGroups()) {
            if (Config.getConfigData().supportGroups.contains(serverGroup))
                return;
        }
        ChannelInfo channelInfo = api.getChannelInfo(e.getTargetChannelId());
        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < Config.getConfigData().supportGroups.size(); i++) {
            List<ServerGroupClient> sClients = api.getServerGroupClients(Config.getConfigData().supportGroups.get(i));
            for (ServerGroupClient sClient : sClients) {
                if(api.isClientOnline(sClient.getUniqueIdentifier())) {
                    Client supporter = api.getClientByUId(sClient.getUniqueIdentifier());
                    if (clients.parallelStream().noneMatch(sup -> sup.getUniqueIdentifier()
                            .equalsIgnoreCase(sClient.getUniqueIdentifier())))
                        clients.add(supporter);
                }
            }
        }
        String sendHelp = clients.size() > 0 ? "Momentan " + (clients.size() > 1 ? "are ":" is") +
                        " [color=" + Config.getColors().mainColor + "][b]" + clients.size() + " Supporter[/b][/color] online! " +
                        "Support will Help you immediately!" :
                        "Unfortunately at the moment there is [color=" + Config.getColors().mainColor + "][b]No Supporter[/b][/color] online. " +
                        "Please come back at a later date!";
        api.sendPrivateMessage(client.getId(), sendHelp);

        clients.parallelStream().forEach(supporter -> api.sendPrivateMessage(supporter.getId(),
                "[color=" + Config.getColors().mainColor + "][b]" + client.getNickname() + "[/b][/color] " +
                "waiting in [color=" + Config.getColors().mainColor + "][b]" + channelInfo.getName() + "[/b][/color] for help!"));
    }

    public static void lockChannel(ClientMovedEvent e, Client client) {
        if (!Config.getConfigData().supportChannels.contains(e.getTargetChannelId())) return;
        boolean isSupporter = false;
        for (int serverGroup : client.getServerGroups()) {
            if (Config.getConfigData().supportGroups.parallelStream().anyMatch(group -> group == serverGroup))
                isSupporter = true;
        }

        if (!api.getChannelInfo(e.getTargetChannelId()).getName().contains("(Teamspeak & Discord)") && isSupporter) {
            ChannelInfo channelInfo = api.getChannelInfo(e.getTargetChannelId());

            final Map<ChannelProperty, String> properties = new HashMap<>();
            properties.put(ChannelProperty.CHANNEL_NAME, channelInfo.getName() + " (Teamspeak & Discord)");
            properties.put(ChannelProperty.CHANNEL_PASSWORD, "");

            api.editChannel(e.getTargetChannelId(), properties);
        }
    }

    public static void unlockChannel() {
        Config.getConfigData().supportChannels.parallelStream().forEach(channelID -> {
            ChannelInfo channelInfo = api.getChannelInfo(channelID);
            Channel channel = api.getChannelByNameExact(channelInfo.getName(), true);

            if (channel.getTotalClients() <= 0 && channelInfo.getName().contains("(Teamspeak & Discord)")) {
                String channelName = channelInfo.getName().replace(" (Teamspeak & Discord)", "");

                final Map<ChannelProperty, String> properties = new HashMap<>();
                properties.put(ChannelProperty.CHANNEL_NAME, channelName);
                properties.put(ChannelProperty.CHANNEL_PASSWORD, "");

                api.editChannel(channelID, properties);
            }
        });
    }
}
