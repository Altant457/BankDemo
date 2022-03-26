package com.example.bankdemo;

import DomainObjects.Account;
import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    Map<String, Account> accountMap = new TreeMap<>();

    public void init() {
        Account acc1 = new Account("Markus", "1", 100);
        Account acc2 = new Account("Sebastian", "1", 100);
        Account acc3 = new Account("Timi", "1", 100);
        Account acc4 = new Account("March", "1", 100);
        Account acc5 = new Account("Patrick", "1", 100);
        Account acc6 = new Account("Otto", "1", 100);
        Account acc7 = new Account("Marie", "1", 100);
        Account acc8 = new Account("Nicki", "1", 100);
        Account acc9 = new Account("Micki", "1", 100);
        Account acc10 = new Account("Nikolaj", "1", 100);

        accountMap.put(acc1.getName(), acc1);
        accountMap.put(acc2.getName(), acc2);
        accountMap.put(acc3.getName(), acc3);
        accountMap.put(acc4.getName(), acc4);
        accountMap.put(acc5.getName(), acc5);
        accountMap.put(acc6.getName(), acc6);
        accountMap.put(acc7.getName(), acc7);
        accountMap.put(acc8.getName(), acc8);
        accountMap.put(acc9.getName(), acc9);
        accountMap.put(acc10.getName(), acc10);

        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("accounts", accountMap);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
