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
        boolean err = false;

        if (!passconf.equals(account.getMasterPass())) {
            request.setAttribute("errmsg", "Password er forkert, sletning af konto kunne ikke bekræftes");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
            err = true;
        }

        if (!err) {
            accountMap.remove(account.getMasterName());
            request.getSession().invalidate();
            request.setAttribute("msg", "Konto \"" + account.getMasterName() + "\" blev slettet");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
