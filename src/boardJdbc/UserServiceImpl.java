package boardJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{

    @Override
    public void registerUser(User user) throws Exception {
        String sql = "INSERT INTO users (userid, userpassword, username, userbirth, userphonenumber, createdate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserid());
            pstmt.setString(2, user.getUserpassword());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getUserbirth());
            pstmt.setString(5, user.getUserPhoneNumber());
            pstmt.setDate(6, new java.sql.Date(user.getCreatedate().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("회원가입 실패 - ERROR : " + e.getMessage(), e);
        }
    }

    @Override
    public void removeUser(int userno) throws Exception {
        String sql = "DELETE FROM users WHERE userno = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userno);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("유저 삭제 실패 - ERROR : " + e.getMessage(), e);
        }
    }
}
