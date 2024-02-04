package boardJdbc;

import java.util.Scanner;

public class BoardMenu {
    Scanner sc = new Scanner(System.in);
    BoardServiceImpl boardService = new BoardServiceImpl();
    static UserServiceImpl userService = new UserServiceImpl();

    public void runProgram() throws Exception {
        startMenu();
    }

    public void startMenu() throws Exception {
        System.out.println();
        System.out.println("메뉴: 1.회원 가입 | 2.로그인");
        System.out.print("메뉴 선택: ");

        int cmd = Integer.parseInt(sc.nextLine());
        switch (cmd) {
            case 1-> {
                signin();
                mainMenu();
            }
            case 2 -> {
                while (true) {
                    int state = login();
                    if (state == 1) {
                        System.out.println("[로그인 성공]");
                        mainMenu();
                    } else {
                        System.out.println("존재하지 않는 회원입니다. 다시 로그인 하세요.\n");
                    }
                }
            }
            default ->
                startMenu();
        }
    }

    public void mainMenu() {
        boardService.boardTable();
        System.out.println();
        System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit | 5. UnRegister");
        System.out.print("메뉴 선택: ");
        try {
            int cmd = Integer.parseInt(sc.nextLine());
            switch (cmd) {
                case 1:
                    boardService.create(userService.getUserid());
                    mainMenu();
                case 2:
                    boardService.read();
                    mainMenu();
                case 3:
                    boardService.clear();
                    mainMenu();
                case 4:
                    boardService.exit();
                case 5:
                    unRegister();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            boardService.exit();
        }
    }

    public void signin() throws Exception {
        User user = new User();
        System.out.println("[회원 가입]");
        System.out.print("아이디: ");
        user.setUserid(sc.nextLine());
        System.out.print("비밀번호: ");
        user.setUserpassword(sc.nextLine());
        System.out.print("이름: ");
        user.setUsername(sc.nextLine());
        System.out.print("생년월일(yymmdd): ");
        user.setUserbirth(sc.nextLine());
        System.out.print("휴대폰 번호: ");
        user.setUserPhoneNumber(sc.nextLine());
        System.out.println();

        userService.registerUser(user);
    }

    public int login() {
        System.out.println("[로그인]");
        System.out.print("아이디 입력: ");
        String userid = sc.nextLine();
        System.out.print("비밀번호 입력: ");
        String password = sc.nextLine();
        System.out.println();
        return userService.login(userid, password);
    }

    public void unRegister() throws Exception {
        System.out.println("[회원 탈퇴]");
        User user = userService.findUser(userService.getUserid());
        System.out.println("정말 탈퇴하시겠습니까? (1. Yes | 2. No)");
        String ch = sc.nextLine();

        if (ch.equals("1")) {
            userService.removeUser(user.getUserno());
            System.out.println("탈퇴 완료");
            startMenu();
        } else {
            System.out.println("처음 페이지로 돌아갑니다.");
            mainMenu();
        }
    }
}