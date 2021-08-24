package de.backxtar;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.events.OnClientJoin;
import de.backxtar.events.OnClientLeave;

public class EventManager {
    public static void loadEvents() {
        TS3Api api = TS3Bot.getInstance().api;

        api.registerAllEvents();
        api.addTS3Listeners(new TS3Listener() {
            @Override
            public void onTextMessage(TextMessageEvent textMessageEvent) {
                if (textMessageEvent.getTargetMode() != TextMessageTargetMode.CLIENT
                        || textMessageEvent.getInvokerUniqueId().equalsIgnoreCase(api.whoAmI().getUniqueIdentifier())) return;
                String message = textMessageEvent.getMessage();
                Client client = api.getClientInfo(textMessageEvent.getTargetClientId());

                if (message.startsWith("!")) {
                    String[] command = message.substring(1).split(" ");

                    if (command.length > 0 && !TS3Bot.getInstance().getCmdManager().runCmd(command, api, textMessageEvent, client))
                        api.sendChannelMessage(client.getNickname() + ", dieser Befehl ist mir nicht bekannt!");
                }
            }

            @Override
            public void onClientJoin(ClientJoinEvent clientJoinEvent) {
                OnClientJoin.changeInfo(api);
                OnClientJoin.sendWelcome(api, clientJoinEvent);
                OnClientJoin.gw2ApiReminder(api, clientJoinEvent);
            }

            @Override
            public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {
                OnClientLeave.changeInfo(api);
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
