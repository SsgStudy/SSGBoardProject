package boardJdbc;

import java.util.Date;
import java.util.Scanner;

public class UserMain {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        Scanner scanner = new Scanner(System.in);


        removeUser(scanner, userService);
        registerUser(scanner, userService);
    }

    private static void removeUser(Scanner scanner, UserService userService) {
        try {
            System.out.println("회원 번호를 입력하여 회원 탈퇴를 진행합니다.");
            System.out.print("회원 번호: ");
            int userno = Integer.parseInt(scanner.nextLine());

            // 탈퇴 확인
            System.out.print("정말 탈퇴하시겠습니까? (y/n): ");
            String confirm = scanner.nextLine();

            if ("y".equalsIgnoreCase(confirm)) {
                userService.removeUser(userno);
                System.out.println("회원 탈퇴가 완료되었습니다.");
            } else if ("n".equalsIgnoreCase(confirm)) {
                System.out.println("회원 탈퇴가 취소되었습니다.");
            } else {
                System.out.println("잘못된 입력입니다. 회원 탈퇴가 취소되었습니다.");
            }

        } catch (Exception e) {
            System.err.println("ERROR 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void registerUser(Scanner scanner, UserService userService) {
        try {
            User newUser = new User();

            System.out.println("회원가입을 진행합니다.");
            System.out.print("아이디를 입력하세요: ");
            newUser.setUserid(scanner.nextLine());

            System.out.print("비밀번호를 입력하세요: ");
            newUser.setUserpassword(scanner.nextLine());

            System.out.print("이름을 입력하세요: ");
            newUser.setUsername(scanner.nextLine());

            System.out.print("생년월일(예: 900101)을 입력하세요: ");
            newUser.setUserbirth(scanner.nextLine());

            System.out.print("전화번호를 입력하세요: ");
            newUser.setUserPhoneNumber(scanner.nextLine());

            newUser.setCreatedate(new Date());

            // 사용자 등록
            System.out.println("회원가입 처리 중...");
            userService.registerUser(newUser);
            System.out.println("환영합니다, " + newUser.getUsername() + "님! 회원가입이 완료되었습니다.");

        } catch (Exception e) {
            System.err.println("ERROR 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
