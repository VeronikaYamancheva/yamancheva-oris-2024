package ru.itis.vhsroni.servlet;

import ru.itis.vhsroni.dto.AuthResponse;
import ru.itis.vhsroni.dto.SignUpRequest;
import ru.itis.vhsroni.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/sign_up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email(req.getParameter("email"))
                .nickname(req.getParameter("nickname"))
                .password(req.getParameter("password"))
                .build();

        AuthResponse authResponse = userService.signUp(signUpRequest);
        if (authResponse.getStatus() == 0) {
            resp.sendRedirect("/signIn");
        } else {
            resp.sendRedirect("/error?err=" + authResponse.getStatusDesc());
        }

    }


}
