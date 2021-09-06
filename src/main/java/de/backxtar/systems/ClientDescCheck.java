package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ClientProperty;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.managers.SqlManager;
import de.backxtar.DerGeraet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDescCheck {
    private static TS3Api api = DerGeraet.getInstance().api;

    public static void descChange() {

        try {
            ResultSet resultSet = SqlManager.selectAll("API_Keys");

            while (resultSet.next()) {
                String clientIdentity = resultSet.getString("clientIdentity");
                String accountName = resultSet.getString("accountName");

                if (!api.isClientOnline(clientIdentity)) return;
                Client client = api.getClientByUId(clientIdentity);

                if (api.getClientInfo(client.getId()).getDescription().equalsIgnoreCase(accountName)) return;
                api.editClient(client.getId(), ClientProperty.CLIENT_DESCRIPTION, accountName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
