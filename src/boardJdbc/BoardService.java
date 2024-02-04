package boardJdbc;

import java.sql.SQLException;

public interface BoardService {
    void create();
    void read() throws SQLException, ClassNotFoundException;
    void readOne(int bno);
    void update(int bno);
    void delete(int bno);
    void clear();
    void exit();
}
