package boardJdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BoardDao {

    static Connection conn;

    public BoardDao() throws SQLException, ClassNotFoundException {
        conn =  DBConnection.getConnection();
    }

    public void create() {
    }

    public void read() {
    }

    public void readOne(Integer bno) {
    }

    public int update(int bno, Board board) {
        int row = 0;

        String sql = new StringBuilder()
                .append("UPDATE boards SET ")
                .append("btitle=?,")
                .append("bcontent=?")
                .append("WHERE bno=?")
                .toString();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getBtitle());
            pstmt.setString(2, board.getBcontent());
            pstmt.setInt(3, bno);

            row = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return row;
    }

    public void delete(int inputBno) {
    }

    public void clear() {
    }
}
