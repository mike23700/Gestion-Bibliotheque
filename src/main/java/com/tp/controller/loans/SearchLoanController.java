package com.tp.controller.loans;

import com.tp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SearchLoanController extends HttpServlet {
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session == null )? null : (User) session.getAttribute("user");

        if(session == null || currentUser.getRole().equals("ADMIN")){
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

    }
}
