package com.example.jdbc.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String username=req.getParameter("username");
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        int phone=Integer.parseInt(req.getParameter("phone"));
        String dob=req.getParameter("dob");
        String account_type=req.getParameter("account_type");
        int balance=Integer.parseInt(req.getParameter("amount"));

        boolean inserted = insertUserData(username,email,phone,password,account_type,dob,balance);
        if (!inserted) {
            PrintWriter out = res.getWriter();
            out.println("Couldn't insert the data into the database. Please try again.");
        } else {
            res.sendRedirect("welcome.jsp");
        }
    }
    private boolean insertUserData(String username, String email,int phone, String password,String account_type,String dob,int balance){
        String url = "jdbc:mysql://localhost:3306/banking";
        String user = "root";
        String accountPassword = "Life123!";
        try (Connection conn = DriverManager.getConnection(url, user, accountPassword)) {
            String sql = "INSERT INTO users( username,email,phone,password,account_type,dob,balance) VALUES (?, ?, ?,?,?,?,?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setInt(3, phone);
                stmt.setString(4,password);
                stmt.setString(5,account_type);
                stmt.setString(6,dob);
                stmt.setInt(7,balance);
                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute database operation: " + e.getMessage());
        }
    }
}
