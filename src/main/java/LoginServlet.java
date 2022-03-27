import DomainObjects.Account;
import DomainObjects.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        boolean err = false;
        Map<String, Account> accountMap = (Map<String, Account>) getServletContext().getAttribute("accounts");
        Account account = null;

        for (String masterName : accountMap.keySet()) {
            for (User user : accountMap.get(masterName).getUsers().keySet()) {
                if (user.getName().equals(name)) {
                    account = accountMap.get(name);
                } else {
                    account = null;
                }
            }
        }

        if (account == null) { // Konto eksisterer ikke
            request.setAttribute("msg", "Kontoen kunne ikke findes.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        } else if (!account.getUserByName(name).getPass().equals(pass)) { // Koden er forkert
            request.setAttribute("msg", "Den angivne kode passer ikke.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        }

        // Login success
        if (!err) {
            HttpSession session = request.getSession();
            session.setAttribute("username", name);
            session.setAttribute("konto", account);
            request.getRequestDispatcher("WEB-INF/brugerSide.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newname = request.getParameter("newname");
        String pass = request.getParameter("newpass");
        String passconf = request.getParameter("passconfirm");
        String type = request.getParameter("type");
        boolean err = false;
        boolean nameAlreadyInUse = false;
        Map<String, Account> accountMap = (Map<String, Account>) getServletContext().getAttribute("accounts");
        List<User> userList = (List<User>) getServletContext().getAttribute("users");

        for (String masterName : accountMap.keySet()) {
            if (!(accountMap.get(masterName).getUserByName(newname) == null)) {
                nameAlreadyInUse = true;
            }
        }

        if (nameAlreadyInUse) {
            request.setAttribute("msg", "Navnet \"" + newname + "\" er allerede i brug, vælg et andet");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        }

        if (!pass.equals(passconf) || err) { // passwords don't match
            request.setAttribute("msg", "Passwords skal være det samme i begge bokse");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            err = true;
        }

        if (!err) {
            if (type.equals("Konto")) {
                User user = new User(newname, pass);
                Account account = new Account(user, 0);
                accountMap.put(newname, account);
                userList.add(user);
                request.setAttribute("msg", "Konto \"" + newname + "\" oprettet, du kan nu logge ind");
            } else if (type.equals("User")) {
                User user = new User(newname, pass);
                userList.add(user);
                request.setAttribute("msg", "Bruger \"" + newname + "\" oprettet, du kan nu logge ind");
            }


            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
