import DomainObjects.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        boolean err = false;
        Map<String, Account> accountMap = (Map<String, Account>) getServletContext().getAttribute("accounts");

        Account account = accountMap.getOrDefault(name, null);

        if (account == null) { // Konto eksisterer ikke
            request.setAttribute("msg", "Kontoen kunne ikke findes.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        } else if (!account.getPass().equals(pass)) { // Koden er forkert
            request.setAttribute("msg", "Den angivne kode passer ikke.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        }

        // Login success
        if (!err) {
            HttpSession session = request.getSession();
            session.setAttribute("konto", account);
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newname = request.getParameter("newname");
        String pass = request.getParameter("newpass");
        String passconf = request.getParameter("passconfirm");
        boolean err = false;
        Map<String, Account> accountMap = (Map<String, Account>) getServletContext().getAttribute("accounts");

        if (accountMap.containsKey(newname)) { // name already in use
            request.setAttribute("msg", "Navnet \"" + newname + "\" er allerede i brug, vælg et andet");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        }

        if (!pass.equals(passconf)) { // passwords don't match
            request.setAttribute("msg", "Passwords skal være det samme i begge bokse");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        }

        if (!err) {
            Account account = new Account(newname, pass, 0);
            accountMap.put(newname, account);
            request.setAttribute("msg", "Konto \"" + newname + "\" oprettet, du kan nu logge ind");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
