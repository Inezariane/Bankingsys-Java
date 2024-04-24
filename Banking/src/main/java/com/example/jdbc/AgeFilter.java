package com.example.jdbc;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

@WebFilter("/register")
public class AgeFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String dobString = req.getParameter("dob");

        try {
            LocalDate dob = LocalDate.parse(dobString);
            int age = calculateAge(dob);

            if (age >= 18) {
                chain.doFilter(request, response);
            } else {
                res.getWriter().println("You must be above 18 years old to create an account in the bank.");
            }
        } catch (DateTimeParseException e) {
            res.getWriter().println("Invalid date of birth format. Please use the format yyyy-MM-dd.");
        }
    }

    public void destroy() {
        // Cleanup code, if needed
    }

    private int calculateAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dob, currentDate).getYears();
    }
}
