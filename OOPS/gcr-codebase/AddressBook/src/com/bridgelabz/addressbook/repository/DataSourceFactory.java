package com.bridgelabz.addressbook.repository;

/**
 * Factory class for creating data source instances.
 * Follows the Open-Close Principle by allowing new data source types
 * to be added without modifying this factory.
 */
public class DataSourceFactory {

    /**
     * Creates a data source based on the specified type
     * @param type The type of data source ("json", "csv", "database", "mysql", "sqlite", "postgresql")
     * @param connectionInfo Connection information (path, URL, etc.)
     * @return IAddressBookRepository instance
     */
    public static IAddressBookRepository createDataSource(String type, String connectionInfo) {
        switch (type.toLowerCase()) {
            case "json":
                if (connectionInfo != null && !connectionInfo.isEmpty()) {
                    return new JsonDataSource(connectionInfo);
                }
                return new JsonDataSource();

            case "csv":
                if (connectionInfo != null && !connectionInfo.isEmpty()) {
                    return new CsvDataSource(connectionInfo);
                }
                return new CsvDataSource();

            case "database":
            case "sqlite":
                if (connectionInfo != null && !connectionInfo.isEmpty()) {
                    return DatabaseDataSource.createSQLite(connectionInfo);
                }
                return new DatabaseDataSource();

            case "mysql":
                // connectionInfo format: host/database/user/password
                return parseConnectionInfo(connectionInfo, "MySQL");

            case "postgresql":
            case "postgres":
                // connectionInfo format: host/database/user/password
                return parseConnectionInfo(connectionInfo, "PostgreSQL");

            default:
                System.out.println("Unknown data source type: " + type + ". Using JSON as default.");
                return new JsonDataSource();
        }
    }

    /**
     * Parses connection info string and creates appropriate database data source
     * Format: host|database|user|password
     */
    private static IAddressBookRepository parseConnectionInfo(String connectionInfo, String databaseType) {
        if (connectionInfo == null || connectionInfo.isEmpty()) {
            System.out.println("Connection info required for " + databaseType + ". Using default SQLite.");
            return new DatabaseDataSource();
        }

        String[] parts = connectionInfo.split("\\|");
        if (parts.length >= 4) {
            String host = parts[0];
            String database = parts[1];
            String user = parts[2];
            String password = parts[3];

            switch (databaseType) {
                case "MySQL":
                    return DatabaseDataSource.createMySQL(host, database, user, password);
                case "PostgreSQL":
                    return DatabaseDataSource.createPostgreSQL(host, database, user, password);
            }
        } else if (parts.length == 1) {
            // Assume it's a file path for SQLite
            return DatabaseDataSource.createSQLite(parts[0]);
        }

        System.out.println("Invalid connection info format for " + databaseType + ". Using default.");
        return new DatabaseDataSource();
    }

    /**
     * Creates a data source from configuration string.
     * Format: type:connectionInfo (e.g., "json:data.json" or "mysql:localhost|mydb|user|pass")
     */
    public static IAddressBookRepository createFromConfig(String config) {
        if (config == null || config.isEmpty()) {
            return new JsonDataSource();
        }

        String[] parts = config.split(":", 2);
        String type = parts[0];
        String connectionInfo = parts.length > 1 ? parts[1] : null;

        return createDataSource(type, connectionInfo);
    }

    /**
     * Gets a list of supported data source types
     */
    public static String[] getSupportedTypes() {
        return new String[] {
            "JSON",
            "CSV",
            "Database (SQLite)",
            "MySQL",
            "PostgreSQL"
        };
    }
}

