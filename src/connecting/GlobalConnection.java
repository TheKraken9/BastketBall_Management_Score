package connecting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GlobalConnection {
    private String username;
    private String password;
    private String port;
    private String database;

    private GlobalConnection(String username, String password, String port) {
        this.username = username;
        this.password = password;
        this.port = port;
    }

    private GlobalConnection(String username, String password, String port, String database) {
        this.username = username;
        this.password = password;
        this.port = port;
        this.database = database;
    }

    private Connection getOracleConnection() throws SQLException {
        Connection co;
        co = DriverManager.getConnection("jdbc:oracle:thin:@localhost:"+port+":orcl",username,password);
        return  co;
    }

    private Connection getPostgreConnection() throws SQLException{
        Connection co;
        co = DriverManager.getConnection("jdbc:postgresql://localhost:"+port+"/"+database,username,password);
        return  co;
    }

    public static Connection connectToOracle() throws SQLException {
        Connection co;
        GlobalConnection con = new GlobalConnection("scott","tiger","1521");
        co = con.getOracleConnection();
        return co;
    }

    public static Connection connectToPostgresql() throws SQLException {
        Connection co;
        GlobalConnection con = new GlobalConnection("postgres","root","5432","basketball");
        co = con.getPostgreConnection();
        return co;
    }
}
