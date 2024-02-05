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

