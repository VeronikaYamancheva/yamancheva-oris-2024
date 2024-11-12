package ru.vhsroni;

import ru.vhsroni.repl.Authorization;
import ru.vhsroni.repl.MethodsUtil;
import ru.vhsroni.service.impl.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        while (true) {
            String token = Authorization.authorize();
            while (userService.signInByToken(token).getStatus() == 0) {
                MethodsUtil.invokeMethod();
                if (Authorization.quit()) {
                    token = null;
                    break;
                }
            }
        }
    }
}