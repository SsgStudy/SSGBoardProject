package boardJdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BoardDao {

    Connection conn;
    public BoardDao() throws SQLException, ClassNotFoundException {
        conn =  DBConnection.getConnection();
    }

    public void create(Board board) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://27.96.130.40", "yeobin", "pw")) {
            String sql = "INSERT INTO board(btitle, bcontent, bwriter, bdate) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, board.getBtitle());
                pstmt.setString(2, board.getBcontent());
                pstmt.setString(3, board.getBwriter());
                pstmt.setString(4, board.getDate());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void read() {
    }

    public void readOne(Integer bno) {
    }

    public void update(Integer bno) {
    }

    public void delete(int inputBno) {
    }

    public void clear() {
    }
}
