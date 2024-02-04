package boardJdbc;

public interface UserService {
    User findUser(String userid) throws Exception;
    void registerUser(User user) throws Exception;
    void removeUser(int userno) throws Exception;
    int login(String id, String password) throws Exception;
}
