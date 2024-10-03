package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bank")
public class BankServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "root", "password");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM bank_details");
            ResultSet rs = ps.executeQuery();

            out.println("<h2>Bank Details:</h2>");
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Account Number</th><th>Balance</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("name") + "</td><td>" 
                    + rs.getString("account_number") + "</td><td>" + rs.getDouble("balance") + "</td></tr>");
            }
            out.println("</table>");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }

        out.println("</body></html>");
    }
}
