package ru.itis.vhsroni.servlet;

import ru.itis.vhsroni.dto.AuthResponse;
import ru.itis.vhsroni.dto.SignInRequest;
import ru.itis.vhsroni.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private UserService userService;

    private String AUTHORIZATION;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        userService = (UserService) context.getAttribute("userService");
        AUTHORIZATION = (String) context.getAttribute("AUTHORIZATION");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInRequest signUpRequest = SignInRequest.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        AuthResponse authResponse = userService.signIn(signUpRequest);
        if(authResponse.getStatus() == 0) {
            HttpSession session = req.getSession(true);
            session.setAttribute(AUTHORIZATION, true);
            session.setAttribute("user", authResponse.getUser());
            resp.sendRedirect("/main");
        } else {
            resp.sendRedirect("/error?err=" + authResponse.getStatusDesc());
        }

    }

}
