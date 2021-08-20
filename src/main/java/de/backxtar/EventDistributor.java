package de.backxtar;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.*;
import de.backxtar.events.OnClientJoin;
import de.backxtar.events.OnClientLeave;

public class EventDistributor {
    public static void loadEvents() {
        TS3Api api = TS3Bot.ts3Bot.api;

        api.registerAllEvents();
        api.addTS3Listeners(new TS3Listener() {
            @Override
            public void onTextMessage(TextMessageEvent textMessageEvent) {

            }

            @Override
            public void onClientJoin(ClientJoinEvent clientJoinEvent) {
                OnClientJoin.changeInfo(api);
                OnClientJoin.sendWelcome(api, clientJoinEvent);
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
