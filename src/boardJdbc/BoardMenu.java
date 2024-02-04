package boardJdbc;

import java.util.Scanner;

public class BoardMenu {

    Scanner sc = new Scanner(System.in);
    BoardDao boardDao = new BoardDao();

    public void runProgram() {
        boardDao.boardTable();
        mainMenu();
    }

    public void mainMenu() {
        System.out.println();
        System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
        System.out.print("메뉴 선택: ");
        try {
            int cmd = Integer.parseInt(sc.nextLine());
            switch (cmd) {
                case 1:
                    boardDao.create();
                    mainMenu();
                case 2:
                    boardDao.read();
                    mainMenu();
                case 3:
                    boardDao.clear();
                    mainMenu();
                case 4:
                    boardDao.exit();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            boardDao.exit();
        }
    }
}