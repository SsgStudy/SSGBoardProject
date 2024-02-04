package boardJdbc;

public interface UserService {
    void registerUser(User user) throws Exception;
    void removeUser(int userno) throws Exception;

}
