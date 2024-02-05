package boardJdbc;

import java.util.Scanner;

public class BoardMenu {
    static UserServiceImpl userService = UserServiceImpl.getInstance();
    private BoardServiceImpl boardService = new BoardServiceImpl();
    Scanner sc = new Scanner(System.in);

    public void runProgram() throws Exception {
        startMenu();
    }

    public void startMenu() throws Exception {
        System.out.println();
        System.out.println("메뉴: 1.회원 가입 | 2.로그인");
        System.out.print("메뉴 선택: ");

        String cmd = sc.nextLine().trim();
        switch (cmd) {
            case "1"-> {
                signin();
                login();
            }
            case "2" -> {
                login();
            }
            default -> {
                System.out.println("잘못된 입력 입니다.");
                startMenu();
            }
        }
    }

    public void mainMenu() {
        boardService.boardTable();
        System.out.println();
        System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit | 5. UnRegister");
        System.out.print("메뉴 선택: ");
        try {
            String cmd = sc.nextLine().trim();

            switch (cmd) {
                case "1" -> {
                    System.out.println("현재 유저아이디 : " + userService.getUserid());
                    boardService.create(userService.getUserid());
                    mainMenu();
                }
                case "2" -> {
                    boardService.read();
                    mainMenu();
                }
                case "3" -> {
                    boardService.clear();
                    mainMenu();
                }
                case "4" -> boardService.exit();
                case "5" -> unRegister();
                default -> {
                    System.out.println("잘못된 입력 입니다.");
                    mainMenu();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            boardService.exit();
        }
    }

    public void signin() throws Exception {
        User user = new User();
        System.out.println("[회원 가입]");
        String userid;
        int row;

        do {
            System.out.print("아이디를 입력하세요: ");
            userid = sc.nextLine().trim();
            if (userid.isEmpty()) {
                System.out.println("아이디는 공백일 수 없습니다. 다시 입력해주세요.");
            }
            row = userService.existUserId(userid);
            if (row == 1) {
                System.out.println("이미 존재하는 아이디 입니다. 다시 입력해주세요.");
            }
        } while (userid.isEmpty() || row == 1);
        user.setUserid(userid);

        String password;
        do {
            System.out.print("비밀번호를 입력하세요: ");
            password = sc.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("비밀번호는 공백일 수 없습니다. 다시 입력해주세요.");
            }
        } while (password.isEmpty());
        user.setUserpassword(password);

        String username;
        do {
            System.out.print("이름을 입력하세요: ");
            username = sc.nextLine().trim();
            if (username.isEmpty()) {
                System.out.println("이름은 공백일 수 없습니다. 다시 입력해주세요.");
            }
        } while (username.isEmpty());
        user.setUsername(username);

        String userbirth;
        do {
            System.out.print("생년월일(예: 900101)을 입력하세요: ");
            userbirth = sc.nextLine().trim();
            if (!userbirth.matches("\\d{6}")) {
                System.out.println("생년월일은 숫자 6자리여야 합니다. 다시 입력해주세요.");
            }
        } while (!userbirth.matches("\\d{6}"));
        user.setUserbirth(userbirth);

        String phoneNumber;
        do {
            System.out.print("전화번호를 입력하세요: ");
            phoneNumber = sc.nextLine().trim();
            if (!phoneNumber.matches("\\d+")) {
                System.out.println("전화번호는 숫자만 포함해야 합니다. 다시 입력해주세요.");
            }
        } while (!phoneNumber.matches("\\d+"));
        user.setUserPhoneNumber(phoneNumber);

        System.out.println();

        userService.registerUser(user);
    }

    public void login() {
        System.out.println("[로그인]");
        System.out.print("아이디 입력: ");
        String userid = sc.nextLine();
        System.out.print("비밀번호 입력: ");
        String password = sc.nextLine();
        System.out.println();

        while (true) {
            int state = userService.login(userid, password);
            if (state == 1) {
                System.out.println("[로그인 성공]");
                mainMenu();
            } else {
                System.out.println("존재하지 않는 회원입니다. 다시 로그인 하세요.\n");
                login();
            }
        }
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