package academic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDatabase {
    static final String US = "root";
    static final String PS = "Mulyad1yam1n.";

    protected String url = "jdbc:mysql://localhost:3306/DBAcademic";
    protected Connection connection = null;
    
    public AbstractDatabase(String url) throws SQLException {
        this.url = url;
        this.prepareTables();
    }

    protected Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, US, PS);
        }
        return connection;
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    protected abstract void prepareTables() throws SQLException;{
        this.createTables();
    }

    protected abstract void createTables() throws SQLException;{
        
    }


}
