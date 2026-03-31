package com.bridgelabz.addressbook.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.addressbook.model.Person;

/**
 * Database implementation of IAddressBookRepository using JDBC.
 * Stores and retrieves contacts from a relational database.
 * 
 * Note: Requires JDBC driver to be available in classpath.
 * For MySQL, use mysql-connector-java jar.
 * For SQLite, use sqlite-jdbc jar.
 */
public class DatabaseDataSource implements IAddressBookRepository {

    private String jdbcUrl;
    private String username;
    private String password;
    private String tableName;
    private String databaseType;

    /**
     * Creates a database data source with default settings (SQLite)
     */
    public DatabaseDataSource() {
        this("jdbc:sqlite:addressbook.db", "", "", "contacts", "SQLite");
    }

    /**
     * Creates a database data source with custom JDBC settings
     * @param jdbcUrl JDBC connection URL
     * @param username Database username
     * @param password Database password
     * @param tableName Table name for contacts
     * @param databaseType Display name for the database type
     */
    public DatabaseDataSource(String jdbcUrl, String username, String password, String tableName, String databaseType) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.tableName = tableName;
        this.databaseType = databaseType;
    }

    @Override
    public void saveContacts(List<Person> contacts) {
        try (Connection connection = getConnection()) {
            // Create table if not exists
            createTable(connection);

            // Clear existing data
            clearTable(connection);

            // Insert contacts
            String sql = "INSERT INTO " + tableName + 
                    " (firstName, lastName, city, state, address, zip, mobile) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (Person person : contacts) {
                    statement.setString(1, person.getFirstName());
                    statement.setString(2, person.getLastName());
                    statement.setString(3, person.getCity());
                    statement.setString(4, person.getState());
                    statement.setString(5, person.getAddress());
                    statement.setInt(6, person.getZip());
                    statement.setLong(7, person.getPhonenumber());
                    statement.addBatch();
                }
                statement.executeBatch();
            }

            System.out.println("Contacts saved to database successfully!");
        } catch (SQLException e) {
            System.err.println("Error saving to database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> loadContacts() {
        List<Person> contacts = new ArrayList<>();

        try (Connection connection = getConnection()) {
            createTable(connection);

            String sql = "SELECT * FROM " + tableName;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    Person person = new Person();
                    person.setFirstName(resultSet.getString("firstName"));
                    person.setLastName(resultSet.getString("lastName"));
                    person.setCity(resultSet.getString("city"));
                    person.setState(resultSet.getString("state"));
                    person.setAddress(resultSet.getString("address"));
                    person.setZip(resultSet.getInt("zip"));
                    person.setPhonenumber(resultSet.getLong("mobile"));
                    contacts.add(person);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error loading from database: " + e.getMessage());
            e.printStackTrace();
        }

        return contacts;
    }

    /**
     * Creates the contacts table if it doesn't exist
     */
    private void createTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstName TEXT NOT NULL, " +
                "lastName TEXT NOT NULL, " +
                "city TEXT, " +
                "state TEXT, " +
                "address TEXT, " +
                "zip INTEGER, " +
                "mobile BIGINT" +
                ")";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    /**
     * Clears all existing contacts from the table
     */
    private void clearTable(Connection connection) throws SQLException {
        String sql = "DELETE FROM " + tableName;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    /**
     * Gets a database connection
     */
    private Connection getConnection() throws SQLException {
        if (username == null || username.isEmpty()) {
            return DriverManager.getConnection(jdbcUrl);
        }
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    @Override
    public String getDataSourceType() {
        return "Database (" + databaseType + ")";
    }

    // Static factory methods for common database types

    /**
     * Creates a MySQL database data source
     */
    public static DatabaseDataSource createMySQL(String host, String database, String user, String password) {
        String jdbcUrl = "jdbc:mysql://" + host + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true";
        return new DatabaseDataSource(jdbcUrl, user, password, "contacts", "MySQL");
    }

    /**
     * Creates a PostgreSQL database data source
     */
    public static DatabaseDataSource createPostgreSQL(String host, String database, String user, String password) {
        String jdbcUrl = "jdbc:postgresql://" + host + "/" + database;
        return new DatabaseDataSource(jdbcUrl, user, password, "contacts", "PostgreSQL");
    }

    /**
     * Creates a SQLite database data source
     */
    public static DatabaseDataSource createSQLite(String filePath) {
        String jdbcUrl = "jdbc:sqlite:" + filePath;
        return new DatabaseDataSource(jdbcUrl, "", "", "contacts", "SQLite");
    }
}

