package boardJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection conn;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        if (conn == null || conn.isClosed() || !conn.isValid(1)) // 연결이 닫혀있거나 유효하지 않으면 새로운 연결 생성

        {
            String url = "jdbc:mysql://27.96.130.40:3306/SSGSTRG";
            String user = "zzzjinwook";
            String pw = "pw";
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, pw);
        }

        return conn;
    }

    public static Connection getConnection(String url, String user, String pw) throws ClassNotFoundException, SQLException {
        if (conn == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pw);
        }

        return conn;
    }

    public static void close() throws SQLException {
        if (conn != null) {
            if (!conn.isClosed()) {
                conn.close();
            }
        }

        conn = null;
    }
}
