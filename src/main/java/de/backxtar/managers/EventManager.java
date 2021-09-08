package de.backxtar.managers;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.DerGeraet;
import de.backxtar.events.OnClientJoin;
import de.backxtar.events.OnClientLeave;
import de.backxtar.systems.ClientHelpReminder;
import de.backxtar.systems.Utils;

public class EventManager {
    public static void loadEvents() {
        TS3Api api = DerGeraet.getInstance().api;

        api.registerAllEvents();
        api.addTS3Listeners(new TS3Listener() {
            @Override
            public void onTextMessage(TextMessageEvent textMessageEvent) {
                if (textMessageEvent.getTargetMode() != TextMessageTargetMode.CLIENT
                        || textMessageEvent.getInvokerUniqueId().equalsIgnoreCase(api.whoAmI().getUniqueIdentifier())) return;
                String message = textMessageEvent.getMessage();
                Client client = api.getClientByUId(textMessageEvent.getInvokerUniqueId());

                if (message.startsWith(Config.getConfigData().prefix)) {
                    String[] command = message.substring(Config.getConfigData().prefix.length()).split(" ");

                    if (command.length < 2) {
                        command = new String[2];
                        command[0] = message.substring(Config.getConfigData().prefix.length());
                        command[1] = "";
                    }

                    if (!DerGeraet.getInstance().getCmdManager().runCmd(command, api, textMessageEvent, client))
                        api.sendChannelMessage("[color=red]✘[/color] Dieser Befehl ist mir nicht bekannt, [b]" + client.getNickname() + "[/b]!");
                }
            }

            @Override
            public void onClientJoin(ClientJoinEvent clientJoinEvent) {
                if (api.getClientInfo(clientJoinEvent.getClientId()).isServerQueryClient()) return;
                Utils.changeInfo();
                OnClientJoin.sendWelcome(clientJoinEvent);
                OnClientJoin.gw2ApiReminder(clientJoinEvent);
            }

            @Override
            public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {
                Utils.changeInfo();
            }

            @Override
            public void onServerEdit(ServerEditedEvent serverEditedEvent) {

            }

            @Override
            public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {

            }

            @Override
            public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {

            }

            @Override
            public void onClientMoved(ClientMovedEvent clientMovedEvent) {
                ClientHelpReminder.doSupport(clientMovedEvent);
            }

            @Override
            public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {

            }

            @Override
            public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {

            }

            @Override
            public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {

            }

            @Override
            public void onChannelPasswordChanged(ChannelPasswordChangedEvent channelPasswordChangedEvent) {

            }

            @Override
            public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent privilegeKeyUsedEvent) {

            }
        });
    }
}
