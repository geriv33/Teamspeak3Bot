package de.backxtar.gw2;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.google.gson.Gson;
import de.backxtar.SqlManager;
import de.backxtar.TS3Bot;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CallToken {

    public static class GWCallToken {
        public String id;
        public String name;
        public String[] permissions;
    }

    public static GWCallToken getGWCallToken(String token) {
        String json = "";
        try {
            json = Utils.getJson("https://api.guildwars2.com/v2/tokeninfo?access_token=" + token);
        } catch (IOException ignored) {}

        Gson gson = new Gson();
        try {
            return gson.fromJson(json, GWCallToken.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static void checkToken(Client client) {
        TS3Api api = TS3Bot.getInstance().api;
        List<Client> clientList = api.getClients();
        GWCallToken token;

        try {
            String[] fieldsSelect = {"GW2_Key", "accountName"};
            Object[] valuesSelect = {client.getUniqueIdentifier()};
            ResultSet resultSet = SqlManager.select(fieldsSelect, "API_Keys", "clientIdentity = ?", valuesSelect);

            if (resultSet.next()) {
                token = getGWCallToken(resultSet.getString("GW2_Key"));

                if (token == null || token.permissions.length < 10) {
                    if (clientList.contains(client)) {
                        api.sendPrivateMessage(client.getId(),
                                "✘ Dein Gw2-Key ist nicht mehr gültig oder" +
                                        "hat nicht alle Berechtigungen.\n" +
                                        "Du kannst hier einen neuen Gw2-Key erstellen:\n" +
                                        "https://account.arena.net/applications");
                        return;
                    }
                    api.sendOfflineMessage(client.getUniqueIdentifier(),
                            "Gw2_Key ungültig!",
                            "✘ Dein Gw2-Key ist nicht mehr gültig oder" +
                                    "hat nicht alle Berechtigungen.\n" +
                                    "Du kannst hier einen neuen Gw2-Key erstellen:\n" +
                                    "https://account.arena.net/applications");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void checkToken(Client client, String apiKey) {
        TS3Api api = TS3Bot.getInstance().api;
        GWCallToken token;
        CallAccount.GWCallAccount account;

        try {
            String[] fieldsSelect = {"GW2_Key"};
            Object[] valuesSelect = {client.getUniqueIdentifier()};
            ResultSet resultSet = SqlManager.select(fieldsSelect, "API_Keys", "clientIdentity = ?", valuesSelect);

            if (resultSet.next()) {
                if (resultSet.getString("GW2_Key").equalsIgnoreCase(apiKey)) {
                    api.sendPrivateMessage(client.getId(),
                            "Dieser Gw2-Key ist bereits hinterlegt.");
                    return;
                }
                token = getGWCallToken(apiKey);

                if (token != null && token.permissions.length == 10) {
                    account = CallAccount.getAccount(apiKey);

                    String[] fieldsUpdate = {"GW2_Key", "accountName"};
                    Object[] valuesUpdate = {apiKey, account.name, client.getUniqueIdentifier()};
                    SqlManager.update(fieldsUpdate, "API_Keys", "clientIdentity = ?", valuesUpdate);

                    api.sendPrivateMessage(client.getId(),
                            "✔ Dein Gw2-Key wurde aktualisiert.\n" +
                                    "Gw2-Account: " + account.name);
                    return;
                }
            } else {
                token = getGWCallToken(apiKey);

                if (token != null && token.permissions.length == 10) {
                    account = CallAccount.getAccount(apiKey);

                    String[] fieldsInsert = {"clientIdentity", "GW2_Key", "accountName"};
                    Object[] valuesInsert = {client.getUniqueIdentifier(), apiKey, account.name, client.getUniqueIdentifier()};
                    SqlManager.insert("API_Keys", fieldsInsert, valuesInsert);

                    api.sendPrivateMessage(client.getId(),
                            "✔ Dein Gw2-Key wurde hinterlegt.\n" +
                                    "Gw2-Account: " + account.name);
                    return;
                }
            }
            api.sendPrivateMessage(client.getId(),
                    "✘ Dein Gw2-Key ist nicht gültig oder" +
                            "hat nicht alle Berechtigungen.\n" +
                            "Du kannst hier einen neuen Gw2-Key erstellen:\n" +
                            "https://account.arena.net/applications");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValid(Client client) {
        TS3Api api = TS3Bot.getInstance().api;

        try {
            String[] fieldsSelect = {"GW2_Key"};
            Object[] valuesSelect = {client.getUniqueIdentifier()};
            ResultSet resultSet = SqlManager.select(fieldsSelect, "API_Keys", "clientIdentity = ?", valuesSelect);

            if (resultSet.next()) {
                GWCallToken token = getGWCallToken(resultSet.getString("GW2_Key"));
                if (token != null && token.permissions.length == 10) return true;
                else {
                    api.sendPrivateMessage(client.getId(),
                            "✘ Dein Gw2-Key ist ungültig oder hat nicht alle Berechtigungen.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        api.sendPrivateMessage(client.getId(), "✘ Du hast noch keinen Gw2-Key hinterlegt.");
        return false;
    }
}
