package com.tp.controller.loans;

import com.tp.model.Loan;
import com.tp.model.User;
import com.tp.service.LoanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addLoan")
public class AddLoanController extends HttpServlet {

    protected void doGet(HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException {

    }
}
