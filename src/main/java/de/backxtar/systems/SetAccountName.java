package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ClientProperty;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.managers.SqlManager;
import de.backxtar.DerGeraet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SetAccountName {

    public static void descChange() {
        TS3Api api = DerGeraet.getInstance().api;

        try {
            ResultSet resultSet = SqlManager.selectAll("API_Keys");

            while (resultSet.next()) {
                Client client = api.getClientByUId(resultSet.getString("clientIdentity"));
                String accountName = resultSet.getString("accountName");
                if (!api.getClientInfo(client.getId()).getDescription().equalsIgnoreCase(accountName))
                    api.editClient(client.getId(), ClientProperty.CLIENT_DESCRIPTION, accountName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
