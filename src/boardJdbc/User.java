package boardJdbc;

import java.util.Date;
import lombok.Data;

@Data
public class User {

    private int userno;
    private String userid;
    private String userpassword;
    private String username;
    private String userbirth;
    private Date createdate;
    private String userPhoneNumber;

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public int getUserno() {
        return userno;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserbirth() {
        return userbirth;
    }

    public void setUserbirth(String userbirth) {
        this.userbirth = userbirth;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userno=" + userno +
                ", userid='" + userid + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", username='" + username + '\'' +
                ", userbirth='" + userbirth + '\'' +
                ", createdate=" + createdate +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                '}';
    }
}

