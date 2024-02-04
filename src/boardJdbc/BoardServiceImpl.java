package boardJdbc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BoardServiceImpl implements BoardService {

    static Scanner sc = new Scanner(System.in);
    List<Board> boardList = new ArrayList<>();
    static BoardDao dao = new BoardDao();

    public void boardTable() {
        System.out.println();
        System.out.println("[게시물 목록]");
        System.out.println("--".repeat(25));
        System.out.println("no \t writer \t date \t \t title");
        System.out.println("--".repeat(25));

        boardList = dao.read();
        for (Board board : boardList) {
            System.out.printf("%-4s%-12s%-16s%-20s\n", board.getBno(), board.getBwriter(), board.getDate(), board.getBtitle());
        }
        System.out.println("--".repeat(25));
    }

    @Override
    public void create(String userId) {
        Board board = new Board();
        System.out.println();
        System.out.println("[새 게시물 입력]");
        System.out.print("제목: ");
        board.setBtitle(sc.nextLine());
        System.out.print("내용: ");
        board.setBcontent(sc.nextLine());
        board.setBwriter(userId);
        boardList.add(board);
        dao.create(board);
    }

    @Override
    public void read() {
        boardList = dao.read();

        System.out.println();
        System.out.println("[게시물 읽기]");
        boardTable();
        System.out.printf("bno: ");
        Integer bno = Integer.parseInt(sc.nextLine());
        readOne(bno);
    }

    @Override
    public void readOne(int bno) {
        Board board = dao.readOne(bno);

        System.out.println("--".repeat(30));

        System.out.printf("번호 : %s\n", bno);
        System.out.printf("제목 : %s\n", board.getBtitle());
        System.out.printf("내용 : %s\n", board.getBcontent());
        System.out.printf("작성자 : %s\n", board.getBwriter());
        System.out.printf("날짜 : %s\n", board.getDate());

        System.out.println("-------------------------------------------------------------");
        System.out.println("보조 메뉴 : 1.Update | 2.Delete | 3. List");
        System.out.print("메뉴 선택 : ");
        String cmd = sc.nextLine();
        switch (cmd) {
            case "1" -> update(bno, board);
            case "2" -> delete(bno);
            case "3" -> boardTable();
        }
    }

    @Override
    public void update(int bno, Board existingPost) {
        int state = 0;
        System.out.println("[수정 내용 입력]");

        Board updateBoard = new Board();
        System.out.print("제목 : ");
        updateBoard.setBtitle(sc.nextLine());
        System.out.print("내용 : ");
        updateBoard.setBcontent(sc.nextLine());

        System.out.println("-------------------------------------------------------------");
        System.out.println("보조 메뉴 : 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택 : ");
        String menuNum = sc.nextLine();

        if (menuNum.equals("1")) {
            if (updateBoard.getBtitle().isEmpty() || updateBoard.getBtitle() == null)
                updateBoard.setBtitle(existingPost.getBtitle());
            else if (updateBoard.getBcontent().isEmpty() || updateBoard.getBcontent() == null) {
                updateBoard.setBcontent(existingPost.getBcontent());
            }
            state = dao.update(bno, updateBoard);
        }

        else if (menuNum.equals("2")) {
            System.out.println("[업데이트 취소]");
            boardTable();
        }

        if (state == 1) {
            System.out.println("[게시물 수정 완료]");
            boardTable();
        }
        else {
            System.out.println("[게시물 수정 실패]");
        }
    }

    @Override
    public void delete(int bno) {
        boardList.removeIf(row -> row.getBno() == bno);
        System.out.println("==삭제되었습니다==\n");
    }

    @Override
    public void clear() {
        System.out.println("[게시물 전체 삭제");
        boardList.clear();
    }

    @Override
    public void exit() {
        System.out.println("**프로그램 종료**");
        System.exit(0);
    }

    public String getTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(new Date());
        return todayDate;
    }
}
