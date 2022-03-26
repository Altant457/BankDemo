package com.example.bankdemo;

import DomainObjects.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LogOutServlet", value = "/LogOutServlet")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String passconf = request.getParameter("confirmDelete");
        Map<String, Account> accountMap = (Map<String, Account>) getServletContext().getAttribute("accounts");
        Account account = (Account) request.getSession().getAttribute("konto");

        if (!passconf.equals(account.getPass())) {
            request.setAttribute("errmsg", "Password er forkert, sletning af konto kunne ikke bekræftes");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
        }

        accountMap.remove(account.getName());
        request.getSession().invalidate();
        request.setAttribute("msg", "Konto \"" + account.getName() + "\" blev slettet");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
