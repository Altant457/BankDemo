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

        Account account = (Account) request.getSession().getAttribute("konto");

        int i = 0;
        if (!beloeb.equals("")) { // hvis input IKKE er tomt
            i = Integer.parseInt(beloeb);
        }

        if (i > account.getSaldo()) {
            errmsg = "Så mange penge har du ikke";
        } else if (i < 0) {
            errmsg = "Du kan ikke hæve et negativt beløb";
        }
        request.setAttribute("errmsg", errmsg);
        account.withdraw(i);


        request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String beloeb = request.getParameter("indsaetbeloeb");
        String errmsg ="";

        Account account = (Account) request.getSession().getAttribute("konto");

        int i = 0;
        if (!beloeb.equals("")) { // hvis input IKKE er tomt
            i = Integer.parseInt(beloeb);
        }

        if (i < 0) {
            errmsg = "Du kan ikke indsætte et negativt beløb";
        }

        request.setAttribute("errmsg", errmsg);
        account.insert(i);

        request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
    }
}
