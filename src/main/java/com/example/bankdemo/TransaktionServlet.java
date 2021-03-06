package com.example.bankdemo;

import DomainObjects.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TransaktionServlet", value = "/TransaktionServlet")
public class TransaktionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String beloeb = request.getParameter("haevebeloeb");
        String errmsg ="";
        boolean err = false;
        Account account = (Account) request.getSession().getAttribute("konto");

        if (account == null) {
            request.setAttribute("msg", "Du er logget ud, log venligst ind igen");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        }

        int i = 0;
        if (!beloeb.equals("")) { // hvis input IKKE er tomt
            i = Integer.parseInt(beloeb);
        }

        if (i > account.getSaldo()) {
            errmsg = "Så mange penge har du ikke";
            err = true;
        } else if (i < 0) {
            errmsg = "Du kan ikke hæve et negativt beløb";
            err = true;
        } else if (!err) {
            account.withdraw(i);
            request.setAttribute("status", i + " kr. hævet");
        }


        request.setAttribute("errmsg", errmsg);
        request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String beloeb = request.getParameter("indsaetbeloeb");
        String errmsg ="";
        boolean err = false;
        Account account = (Account) request.getSession().getAttribute("konto");

        if (account == null) {
            request.setAttribute("msg", "Du er logget ud, log venligst ind igen");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        }

        int i = 0;
        if (!beloeb.equals("")) { // hvis input IKKE er tomt
            i = Integer.parseInt(beloeb);
        }
        if (i < 0) {
            errmsg = "Du kan ikke indsætte et negativt beløb";
            err = true;
        }

        if (!err) {
            account.insert(i);
            request.setAttribute("status", i + " kr. indsat");
        }

        request.setAttribute("errmsg", errmsg);
        request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
    }
}
