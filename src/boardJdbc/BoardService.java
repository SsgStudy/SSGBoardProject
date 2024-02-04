package boardJdbc;

import java.sql.SQLException;

public interface BoardService {
    void create() throws SQLException, ClassNotFoundException;
    void read();
    void readOne(int bno);
    void update(int bno);
    void delete(int bno);
    void clear();
    void exit();
}
