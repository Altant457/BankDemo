package com.example.bankdemo;

import DomainObjects.Account;
import DomainObjects.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    Map<String, Account> accountMap = new TreeMap<>();
    List<User> userList = new ArrayList<>();

    public void init() {
        User use1 = new User("Markus", "1");
        User use2 = new User("Sebastian", "1");
        User use3 = new User("Timi", "1");
        User use4 = new User("March", "1");
        User use5 = new User("Patrick", "1");
        User use6 = new User("Otto", "1");
        User use7 = new User("Marie", "1");
        User use8 = new User("Nicki", "1");
        User use9 = new User("Micki", "1");
        User use10 = new User("Nikolaj", "1");

        Account acc1 = new Account(use1, 100);
        Account acc2 = new Account(use2, 100);
        Account acc3 = new Account(use3, 100);
        Account acc4 = new Account(use4, 100);
        Account acc5 = new Account(use5, 100);
        Account acc6 = new Account(use6, 100);
        Account acc7 = new Account(use7, 100);
        Account acc8 = new Account(use8, 100);
        Account acc9 = new Account(use9, 100);
        Account acc10 = new Account(use10, 100);

        accountMap.put(acc1.getMasterName(), acc1);
        accountMap.put(acc2.getMasterName(), acc2);
        accountMap.put(acc3.getMasterName(), acc3);
        accountMap.put(acc4.getMasterName(), acc4);
        accountMap.put(acc5.getMasterName(), acc5);
        accountMap.put(acc6.getMasterName(), acc6);
        accountMap.put(acc7.getMasterName(), acc7);
        accountMap.put(acc8.getMasterName(), acc8);
        accountMap.put(acc9.getMasterName(), acc9);
        accountMap.put(acc10.getMasterName(), acc10);

        userList.add(use1);
        userList.add(use2);
        userList.add(use3);
        userList.add(use4);
        userList.add(use5);
        userList.add(use6);
        userList.add(use7);
        userList.add(use8);
        userList.add(use9);
        userList.add(use10);

        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("accounts", accountMap);
        servletContext.setAttribute("users", userList);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
