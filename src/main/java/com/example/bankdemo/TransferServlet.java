package com.example.bankdemo;

import DomainObjects.Account;

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

        Map<String, Account> accountMap = (Map<String, Account>) getServletContext().getAttribute("accounts");
        Account account = (Account) request.getSession().getAttribute("konto");
        Account toaccount = accountMap.getOrDefault(toaccountname, null);

        if (account == null) {
            request.setAttribute("msg", "Du er logget ud, log venligst ind igen");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        if (toaccount == null) {
            request.setAttribute("errmsg", "Den konto findes ikke");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
        }
        if (account == toaccount) {
            request.setAttribute("errmsg", "Du kan ikke overføre penge til dig selv");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
        }
        if (!passconf.equals(account.getPass())) {
            request.setAttribute("errmsg", "Password er forkert, overførslen kunne ikke bekræftes");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
        }
        if (transferAmt < 0) {
            request.setAttribute("errmsg", "Du kan ikke overføre et negativt beløb");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
        }
        if (transferAmt > account.getSaldo()) {
            request.setAttribute("errmsg", "Så mange penge har du ikke på din saldo");
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
        }

        account.transfer(transferAmt, toaccount, true);

        request.setAttribute("status", "Overførsel på " + transferAmt + " kr. til \"" +
                                       toaccount.getName() + "\" fuldført");
        request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
    }
}
