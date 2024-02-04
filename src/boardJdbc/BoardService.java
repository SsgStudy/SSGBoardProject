package boardJdbc;

public interface BoardService {
    void create();
    void read();
    void readOne(int bno);
    void update(int bno);
    void delete(int bno);
    void clear();
    void exit();
}
