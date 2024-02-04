package boardJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {

        if (conn == null)
        {
            String url = "jdbc:mysql://27.96.130.40";
            String user = "yeobin";
            String pw = "pw";
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, pw);
        }

        return conn;
    }

    public static Connection getConnection(String url, String user, String pw) throws ClassNotFoundException, SQLException
    {
        if (conn == null)
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pw);
        }

        return conn;
    }

    public static void close() throws SQLException
    {
        if (conn != null)
        {
            if (!conn.isClosed())
                conn.close();
        }

        conn = null;
    }
}
