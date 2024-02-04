package boardJdbc;


import java.sql.Connection;
import java.sql.SQLException;


public class BoardDao {

    Connection conn;
    public BoardDao() throws SQLException, ClassNotFoundException {
        conn =  DBConnection.getConnection();
    }

    public void create() {
    }

    public void read() {
    }

    public void readOne(Integer bno) {
    }

    public void update(Integer bno) {
    }

    public void delete(int inputBno) {
    }

    public void clear() {
    }
}
