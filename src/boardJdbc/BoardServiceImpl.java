package boardJdbc;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class BoardServiceImpl implements BoardService {

    static Scanner sc = new Scanner(System.in);
    List<Board> boardList = new ArrayList<>();

    static BoardDao dao = new BoardDao();


    int count = 1;

    public void boardTable() {
        System.out.println();
        System.out.println("[게시물 목록]");
        System.out.println("--".repeat(25));
        System.out.println("no \t writer \t date \t \t title");
        System.out.println("--".repeat(25));
        for (Board board : boardList) {
            System.out.printf("%-4s%-12s%-16s%-20s\n", board.getBno(), board.getBwriter(), board.getDate(), board.getBtitle());
        }
        System.out.println("--".repeat(25));
    }

    @Override
    public void create() {
        Board board = new Board();
        System.out.println();
        System.out.println("[새 게시물 입력]");
        System.out.print("제목: ");
        board.setBtitle(sc.nextLine());
        System.out.print("내용: ");
        board.setBcontent(sc.nextLine());
        System.out.print("작성자: ");
        board.setBwriter(sc.nextLine());
        board.setDate(getTodayDate());
        board.setBno(count++);
        boardList.add(board);
    }

    @Override
    public void read() {
        System.out.println();
        System.out.println("[게시물 읽기]");
        boardTable();
        System.out.printf("bno: ");
        Integer bno = Integer.parseInt(sc.nextLine());
        readOne(bno);
    }

    @Override
    public void readOne(int bno) {
        System.out.println("--".repeat(30));
        for (Board board : boardList) {
            if (board.getBno() == bno) {
                System.out.printf("번호 : %s\n", bno);
                System.out.printf("제목 : %s\n", board.getBtitle());
                System.out.printf("내용 : %s\n", board.getBcontent());
                System.out.printf("작성자 : %s\n", board.getBwriter());
                System.out.printf("날짜 : %s\n", board.getDate());
            }
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("보조 메뉴 : 1.Update | 2.Delete | 3. List");
        System.out.print("메뉴 선택 : ");
        String cmd = sc.nextLine();
        switch (cmd) {
            case "1" -> update(bno);
            case "2" -> delete(bno);
            case "3" -> boardTable();
        }
    }

    @Override
    public void update(int bno) {
        System.out.println();
        System.out.println("[수정 내용 입력]");
        for (Board board : boardList) {
            if (board.getBno() == bno) {
                System.out.print("제목 : ");
                board.setBtitle(sc.nextLine());
                System.out.print("내용 : ");
                board.setBcontent(sc.nextLine());
                System.out.print("작성자 : ");
                board.setBwriter(sc.nextLine());
            }
        }

        System.out.println("-------------------------------------------------------------");
        System.out.println("보조 메뉴 : 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택 : ");
        String menuNum = sc.nextLine();
        if (menuNum.equals("1")) {
            System.out.println("[게시물 수정 완료]");
            boardTable();
        }
    }

    @Override
    public void delete(int bno) {
        boardList.removeIf(row -> row.getBno() == bno);

        while (true) {
            System.out.println("보조메뉴 : 1. Y | 2. N");
            System.out.print("메뉴입력 : ");
            String result = sc.nextLine().toLowerCase();
            if (result.equals("y")) {
                dao.delete(bno);
                System.out.printf("%d번 게시글이 삭제되었습니다\n", bno);
                break;
            } else if (result.equals("n")) {
                System.out.println("게시글 삭제를 취소했습니다.");
                break;
            } else {
                System.out.println("알맞은 양식으로 다시 입력해주세요");
            }
        }


    }

    @Override
    public void clear() {
        System.out.println("[게시물 전체 삭제]");

        while (true) {
            System.out.println("보조메뉴 : 1. Y | 2. N");
            System.out.print("메뉴입력 : ");
            String result = sc.nextLine().toLowerCase();
            if (result.equals("y")) {
                dao.clear();
                boardList.clear();
                System.out.println("전체 게시글이 삭제되었습니다\n");
                break;
            } else if (result.equals("n")) {
                System.out.println("게시글 삭제를 취소했습니다.");
                break;
            } else {
                System.out.println("알맞은 양식으로 다시 입력해주세요");
            }
        }
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
