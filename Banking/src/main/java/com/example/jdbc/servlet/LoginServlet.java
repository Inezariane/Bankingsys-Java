package com.example.jdbc.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean exists = userInDB(email, password);
        if (!exists) {
            PrintWriter out = res.getWriter();
            out.println("Wrong email or password");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("email", email);
            session.setAttribute("password", password);

            res.sendRedirect("welcome.jsp");
        }
    }

    private boolean userInDB(String email, String password) {
        String url = "jdbc:mysql://localhost:3306/banking"; // MYSQL JDBC URL
        String user = "root"; // MYSQL username
        String accountPassword = "Life123!"; // MYSQL password
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MYSQL JDBC driver: " + e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(url, user, accountPassword)) {
            String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
            System.out.println("SQL Query: " + sql);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute database operation: " + e.getMessage());
        }
        return false;
    }
}
