package boardJdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class BoardDao {

    Connection conn;

    public BoardDao()  {
        try {
            conn = DBConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create() {
    }

    public void read() {
    }

    public void readOne(int bno) {
    }

    public void update(int bno) {
    }

    public void delete(int bno) {
        String sql = "DELETE FROM boards WHERE bno=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bno);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        String sql = "DELETE FROM boards";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
