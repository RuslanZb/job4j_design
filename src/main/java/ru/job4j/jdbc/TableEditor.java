package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty("driver_class"));
        connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"));
    }

    private void execute(String sql) throws SQLException, ClassNotFoundException {
        initConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public void createTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "create table if not exists %s();", tableName
        );
        execute(sql);
    }

    public void dropTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "drop table %s", tableName
        );
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "alter table %s add column %s %s;", tableName, columnName, type
        );
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "alter table %s drop column %s;", tableName, columnName
        );
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "alter table %s rename column %s to %s;", tableName, columnName, newColumnName
        );
        execute(sql);
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.createTable("book");
        System.out.println(tableEditor.getTableScheme("book"));
        tableEditor.dropTable("book");
        tableEditor.createTable("book");
        tableEditor.addColumn("book", "title", "text");
        tableEditor.addColumn("book", "author", "text");
        System.out.println(tableEditor.getTableScheme("book"));
        tableEditor.dropColumn("book", "author");
        System.out.println(tableEditor.getTableScheme("book"));
        tableEditor.renameColumn("book", "title", "name");
        System.out.println(tableEditor.getTableScheme("book"));
    }
}