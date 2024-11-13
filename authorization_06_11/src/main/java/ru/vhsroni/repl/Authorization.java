package ru.vhsroni.repl;

import lombok.extern.slf4j.Slf4j;
import ru.vhsroni.dto.AuthResponse;
import ru.vhsroni.dto.SignInRequest;
import ru.vhsroni.dto.SignUpRequest;
import ru.vhsroni.model.UserEntity;
import ru.vhsroni.service.impl.UserServiceImpl;

import java.util.Scanner;

@Slf4j
public class Authorization {

    private static final Scanner sc = new Scanner(System.in);

    private static final UserServiceImpl userService = new UserServiceImpl();

    public static String authorize() {
        System.out.println("Select do you want `sign in` or `sign up`:" +
                "\n(enter the required in the line below)");
        boolean needRegister = selectAction();
        if (needRegister) signUp();
        UserEntity user = signIn();
        return user.getToken();
    }

    private static UserEntity signIn() {
        System.out.println("SIGN IN");
        System.out.println("Input your email/nickname:");
        String attr = sc.nextLine();
        System.out.println("Input your password:");
        String password = sc.nextLine();

        SignInRequest request = new SignInRequest(attr, password);
        AuthResponse response = userService.signIn(request);
        if (response.getStatus() != 0) {
            log.warn("Error {} with the status = {} has been detected", response.getStatusDesc(), response.getStatus());
            System.out.println("Fill out the sign in form");
            return signIn();
        } else {
            log.info("User signed in: {}", response.getUser());
            return response.getUser();
        }
    }

    private static void signUp() {
        System.out.println("SIGN UP");
        System.out.println("Input your email:");
        String email = sc.nextLine();
        System.out.println("Input your nickname:");
        String nickname = sc.nextLine();
        System.out.println("Input your password:");
        String password = sc.nextLine();

        SignUpRequest request = new SignUpRequest(email, password, nickname);
        AuthResponse response = userService.signUp(request);
        if (response.getStatus() != 0) {
            log.warn("Error {} with the status = {} has been detected", response.getStatusDesc(), response.getStatus());
            System.out.println("Fill out the sign up form");
            signUp();
        } else {
            log.info("User signed up: {}", response.getUser());
        }
    }

    private static boolean selectAction() {
        String action = sc.nextLine();
        if (action.equals("sign up")) {
            return true;
        } else if (action.equals("sign in")) {
            return false;
        } else {
            System.out.println("Incorrect input. Repeat please:");
            return selectAction();
        }
    }

    public static boolean quit() {
        System.out.println("Do uoy want to quit? (Enter `yes`/`no` in the line below.");
        String answer = sc.nextLine();
        return answer.equals("yes");
    }
}
