package com.example.bankdemo;

import DomainObjects.Account;
import DomainObjects.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PassChangeServlet", value = "/PassChangeServlet")
public class PassChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currPass = request.getParameter("currPass");
        String newPass = request.getParameter("newPass");
        String newPassConf = request.getParameter("newPassConf");
        boolean err = false;
        Account account = (Account) request.getSession().getAttribute("konto");
        User currUser = (User) request.getSession().getAttribute("username");

        if (account == null) {
            request.setAttribute("msg", "Du er logget ud, log venligst ind igen");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        }
        if (!currPass.equals(currUser.getPass()) || err) {
            request.setAttribute("errmsg", "Password er forkert");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
            err = true;
        }
        if (!newPass.equals(newPassConf) || err) {
            request.setAttribute("errmsg", "Ny password er ikke ens i begge felter");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
            err = true;
        }

        if (!err) {
            if (account.getUsers().get(currUser) == 1) {
                account.setMasterPass(newPass);
                currUser.setPass(newPass);
                request.setAttribute("errmsg", "Master password ændret");
            } else {
                currUser.setPass(newPass);
                request.setAttribute("errmsg", "Password ændret");
            }

            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
        }
    }
}
