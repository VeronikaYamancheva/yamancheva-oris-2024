package ru.itis.vhsroni.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private String AUTHORIZATION;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        AUTHORIZATION = (String) context.getAttribute("AUTHORIZATION");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setAttribute(AUTHORIZATION, false);
        resp.sendRedirect("/");
    }
}
