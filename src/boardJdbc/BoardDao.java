package boardJdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BoardDao {

    Connection conn;
    public BoardDao() {
        try {
            conn = DBConnection.getConnection();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        } catch (SQLException s) {
            s.printStackTrace();
        }
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

    public List<Board> read() {
        List<Board> boards = new ArrayList<>();

        try{
            String sql = new StringBuilder().append("SELECT * FROM boards").toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Board board = new Board();
                board.setBno(rs.getInt("bno"));
                board.setBtitle(rs.getString("btitle"));
                board.setBcontent(rs.getString("bcontent"));
                board.setBwriter(rs.getString("bwriter"));
                board.setDate(rs.getDate("bdate"));
                boards.add(board);
            }

            rs.close();
            pstmt.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return boards;

    }

    public Board readOne(Integer bno) {

        Board board = new Board();
        try{
            String sql = new StringBuilder().append("SELECT * FROM boards WHERE bno=?").toString();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,bno);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                board.setBno(rs.getInt("bno"));
                board.setBtitle(rs.getString("btitle"));
                board.setBcontent(rs.getString("bcontent"));
                board.setBwriter(rs.getString("bwriter"));
                board.setDate(rs.getDate("bdate"));
            }
        }catch(SQLException s){
            s.printStackTrace();
        }

        return board;
    }

    public void update(Integer bno) {
    }

    public void delete(int inputBno) {
    }

    public void clear() {
    }
}
