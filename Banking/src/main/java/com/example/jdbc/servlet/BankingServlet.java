package com.example.jdbc.servlet;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


public class BankingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "checkBalance":
                checkBalance(request, response);
                break;
            case "deposit":
                deposit(request, response);
                break;
            case "withdraw":
                withdraw(request, response);
                break;
            case "exit":
                exitApplication(request, response);
                break;
            default:
                response.getWriter().println("Invalid action");
        }
    }
    String url = "jdbc:mysql://localhost:3306/banking";
    String user = "root";
    String accountPassword = "Life123!";

    private void checkBalance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver: " + e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(url, user, accountPassword)) {
            // Check if the email and password combination exists
            validate(email, password, conn);

            // Retrieve the balance for the user
            String balanceSql = "SELECT balance FROM users WHERE email = ?";
            try (PreparedStatement balanceStmt = conn.prepareStatement(balanceSql)) {
                balanceStmt.setString(1, email);
                try (ResultSet balanceRs = balanceStmt.executeQuery()) {
                    if (balanceRs.next()) {
                        int balance = balanceRs.getInt("balance");
                        response.getWriter().println("Balance for " + email + ": $" + balance);
                    } else {
                        throw new RuntimeException("User not found");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute database operation: " + e.getMessage());
        }
    }

    private boolean deposit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int amount = Integer.parseInt(request.getParameter("amount"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver: " + e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(url, user, accountPassword)) {

            validate(email, password, conn);

            // Update the balance for the user
            String updateSql = "UPDATE users SET balance = balance + ? WHERE email = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, amount);
                updateStmt.setString(2, email);
                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected == 1) {
                    return true;
                } else {
                    throw new RuntimeException("Failed to update balance");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute database operation: " + e.getMessage());
        }
    }

    private void validate(String email, String password, Connection conn) throws SQLException {
        String loginSql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement loginStmt = conn.prepareStatement(loginSql)) {
            loginStmt.setString(1, email);
            loginStmt.setString(2, password);
            try (ResultSet loginRs = loginStmt.executeQuery()) {
                if (!loginRs.next()) {
                    throw new RuntimeException("Invalid email or password");
                }
            }
        }
    }

    private void withdraw(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int amount = Integer.parseInt(request.getParameter("amount"));



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver: " + e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(url, user, accountPassword)) {
            // Check if the email and password combination exists
            validate(email, password, conn);

            // Check if the user has sufficient balance for withdrawal
            String balanceSql = "SELECT balance FROM users WHERE email = ?";
            try (PreparedStatement balanceStmt = conn.prepareStatement(balanceSql)) {
                balanceStmt.setString(1, email);
                try (ResultSet balanceRs = balanceStmt.executeQuery()) {
                    if (balanceRs.next()) {
                        int balance = balanceRs.getInt("balance");
                        if (balance < amount) {
                            throw new RuntimeException("Insufficient balance");
                        }
                    } else {
                        throw new RuntimeException("User not found");
                    }
                }
            }

            // Update the balance for the user after withdrawal
            String updateSql = "UPDATE users SET balance = balance - ? WHERE email = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, amount);
                updateStmt.setString(2, email);
                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected == 1) {
                    // Balance updated successfully after withdrawal
                    response.getWriter().println("$" + amount + " withdrawn successfully from " + email + "'s account.");
                } else {
                    throw new RuntimeException("Failed to update balance");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute database operation: " + e.getMessage());
        }
    }


    private void exitApplication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("Exiting the application.");
    }
}

