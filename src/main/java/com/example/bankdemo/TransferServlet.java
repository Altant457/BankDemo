package com.example.bankdemo;

import DomainObjects.Account;
import DomainObjects.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "TransferServlet", value = "/TransferServlet")
public class TransferServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toaccountname = request.getParameter("tokonto");
        int transferAmt = Integer.parseInt(request.getParameter("transferbeloeb"));
        String passconf = request.getParameter("passconf");
        boolean err = false;
        Map<String, Account> accountMap = (Map<String, Account>) getServletContext().getAttribute("accounts");
        Account account = (Account) request.getSession().getAttribute("konto");
        User currUser = (User) request.getSession().getAttribute("username");
        Account toaccount = accountMap.getOrDefault(toaccountname, null);

        if (account == null) {
            request.setAttribute("msg", "Du er logget ud, log venligst ind igen");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        }
        if (toaccount == null || err) {
            request.setAttribute("errmsg", "Den konto findes ikke");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
            err = true;
        }
        if (account == toaccount || err) {
            request.setAttribute("errmsg", "Du kan ikke overføre penge til dig selv");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
            err = true;
        }
        if (!passconf.equals(currUser.getPass()) || err) {
            request.setAttribute("errmsg", "Password er forkert, overførslen kunne ikke bekræftes");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
            err = true;
        }
        if (transferAmt < 0 || err) {
            request.setAttribute("errmsg", "Du kan ikke overføre et negativt beløb");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
            err = true;
        }
        if ((transferAmt > account.getSaldo()) || err) {
            request.setAttribute("errmsg", "Så mange penge har du ikke på din saldo");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
            err = true;
        }

        if (!err) {
            account.transfer(transferAmt, toaccount, true);

            request.setAttribute("status", "Overførsel på " + transferAmt + " kr. til \"" +
                                           toaccount.getMasterName() + "\" fuldført");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
        }
    }
}
