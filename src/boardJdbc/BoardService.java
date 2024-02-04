package boardJdbc;

public interface BoardService {
    void create(String userId);
    void read();
    void readOne(int bno);
    void update(int bno, Board existingPost);
    void delete(int bno);
    void clear();
    void exit();
}
