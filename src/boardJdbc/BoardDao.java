package boardJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BoardDao {
    static Connection conn;

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
        try {
            String sql = "INSERT INTO boards (btitle, bcontent, bwriter) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getBtitle());
            pstmt.setString(2, board.getBcontent());
            pstmt.setString(3, board.getBwriter());
            pstmt.executeUpdate();
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


    public Board readOne(int bno) {
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
            } else {
                return null;
            }

        } catch(SQLException s){
            s.printStackTrace();
        }

        return board;
    }

    public int checkUserBoard(int bno, String userid) {
        int state = 0;

        try{
            String sql = new StringBuilder().append("SELECT * FROM boards WHERE bno=? and bwriter=?").toString();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,bno);
            pstmt.setString(2, userid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                state = 1;
            }
        } catch(SQLException s){
            s.printStackTrace();
        }

        return state;
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

    public void exit() {
        try {
            DBConnection.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
