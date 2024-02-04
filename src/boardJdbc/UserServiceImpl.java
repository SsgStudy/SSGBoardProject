package boardJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{

    private String userid;

    public String getUserid() {
        return userid;
    }

    @Override
    public User findUser(String userid) throws Exception {
        String sql = "SELECT * FROM users WHERE userid= ? ";

        User user = new User();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setUserno(rs.getInt("userno"));
            }
            pstmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new Exception("회원조회 실패 - ERROR : " + e.getMessage(), e);
        }
        return user;
    }

    @Override
    public void registerUser(User user) throws Exception {
        String sql = "INSERT INTO users (userid, userpassword, username, userbirth, userphonenumber) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUserid());
            pstmt.setString(2, user.getUserpassword());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getUserbirth());
            pstmt.setString(5, user.getUserPhoneNumber());
            pstmt.executeUpdate();
            System.out.println("[회원가입 성공]");
            pstmt.close();
        } catch (SQLException e) {
            throw new Exception("회원가입 실패 - ERROR : " + e.getMessage(), e);
        }
    }

    @Override
    public void removeUser(int userno) throws Exception {
        String sql = "DELETE FROM users WHERE userno = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userno);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new Exception("유저 삭제 실패 - ERROR : " + e.getMessage(), e);
        }
    }

    @Override
    public int login(String id, String password) {
        int state = 0;
        String sql = "SELECT * FROM users WHERE userid = ? and userpassword = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                state = 1; // 로그인 성공
                userid = id;
            }

            pstmt.close();

        } catch (SQLException | ClassNotFoundException s) {
            s.printStackTrace();
            state = -1; // 로그인 처리 중 예외 발생
        }
        return state;
    }

}
