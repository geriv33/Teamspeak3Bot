package de.backxtar;

import java.sql.*;
import java.util.Arrays;

public class SqlManager {
    private static Connection connection = null;

    public static void connect() throws ClassNotFoundException, SQLException {
        if (connection != null) disconnect();
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        Class.forName(jdbcDriver);
        connection = DriverManager.getConnection("jdbc:mysql://" + TS3Bot.ts3Bot.configData.dbHost + ":3306/" + TS3Bot.ts3Bot.configData.dbName,
                TS3Bot.ts3Bot.configData.dbUser, TS3Bot.ts3Bot.configData.dbPassword);
        TS3Bot.logger.info("Database connected.");
    }

    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public static void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
                TS3Bot.logger.info("Database disconnected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void checkConnection() {
        try {
            if (connection.isClosed() || connection == null) {
                connect();
                TS3Bot.logger.info("Database reconnected.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            TS3Bot.logger.info("Database reconnect failed.");
        }
    }

    public static void insert(String table, String[] fields, Object[] values) throws SQLException {
        StringBuilder stmtString = new StringBuilder("INSERT INTO " + table + Arrays.toString(fields).replace("[", "(").replace("]", ")") + " VALUES(");
        for (Object ignored : values)
            stmtString.append("?,");
        stmtString = new StringBuilder(stmtString.substring(0, stmtString.length() - 1) + ") ");
        PreparedStatement stmt = prepareStatement(stmtString.toString());

        int i = 1;
        for (Object value : values) {
            if (value.getClass().getName().equals("java.lang.String"))
                stmt.setString(i++, (String) value);
            else stmt.setLong(i++, (Long) value);
        }
        stmt.execute();
    }

    public static void delete(String table, String where, Object[] values) throws SQLException {
        PreparedStatement stmt = prepareStatement("DELETE FROM " + table + " WHERE " + where);

        int i = 1;
        for (Object value : values) {
            if (value.getClass().getName().equals("java.lang.String"))
                stmt.setString(i++, (String) value);
            else stmt.setLong(i++, (Long) value);
        }
        stmt.execute();
    }

    public static void delete(String table) throws SQLException {
        PreparedStatement stmt = prepareStatement("DELETE FROM " + table);
        stmt.execute();
    }

    public static ResultSet select(String[] fields, String table) throws SQLException {
        PreparedStatement stmt = prepareStatement("SELECT " + Arrays.toString(fields).replace("[", "").replace("]", "") + " FROM " + table);
        return stmt.executeQuery();
    }

    public static ResultSet select(String field, String table) throws SQLException {
        PreparedStatement stmt = prepareStatement("SELECT " + field + " FROM " + table);
        return stmt.executeQuery();
    }

    public static ResultSet select(String[] fields, String table, String where, Object[] values) throws SQLException {
        PreparedStatement stmt = prepareStatement("SELECT " + Arrays.toString(fields).replace("[", "").replace("]", "") + " FROM " + table + " WHERE " + where);

        int i = 1;
        for (Object value : values) {
            if (value.getClass().getName().equals("java.lang.String"))
                stmt.setString(i++, (String) value);
            else stmt.setLong(i++, (Long) value);
        }
        return stmt.executeQuery();
    }

    public static ResultSet selectAll(String table, String where, Object[] values) throws NumberFormatException, SQLException {
        PreparedStatement stmt = prepareStatement("SELECT * FROM " + table + " WHERE " + where);

        int i = 1;
        for (Object value : values) {
            if (value.getClass().getName().equals("java.lang.String"))
                stmt.setString(i++, (String) value);
            else stmt.setLong(i++, (Long) value);
        }
        return stmt.executeQuery();
    }

    public static ResultSet selectAll(String table) throws SQLException {
        PreparedStatement stmt = prepareStatement("SELECT * FROM " + table);
        return stmt.executeQuery();
    }

    public static ResultSet selectAllOrderBy(String[] fields, String table, String order, String type) throws SQLException {
        PreparedStatement stmt = prepareStatement("SELECT " + Arrays.toString(fields).replace("[", "").replace("]", "") + " FROM " + table + " ORDER BY " + order + " " + type);
        return stmt.executeQuery();
    }

    public static void update(String[] fields, String table, String where, Object[] values) throws SQLException {
        PreparedStatement stmt = prepareStatement("UPDATE " + table + " SET " + Arrays.toString(fields).replace("[", "").replace("]", " = ?").replace(",", "= ? ,") + " WHERE " + where);

        int i = 1;
        for (Object value : values) {
            if (value.getClass().getName().equals("java.lang.String"))
                stmt.setString(i++, (String) value);
            else stmt.setLong(i++, (Long) value);
        }
        stmt.execute();
    }
}
