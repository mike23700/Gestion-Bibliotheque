package com.tp.service;

import com.tp.dao.DAOFactory;
import com.tp.dao.interfaces.LoanDAO;
import com.tp.model.Loan;

import java.time.LocalDateTime;
import java.util.List;

public class LoanService {
    private LoanDAO loanDAO;

    public LoanService(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.loanDAO = daoFactory.getLoanDAO();
    }

    public void AddLoan(Loan loan) throws Exception {
        loanDAO.AddLoan(loan);
    }

    public void DeleteLoan(String loan_id) throws Exception {
        loanDAO.DeleteLoan(loan_id);
    }

    public List<Loan> getAllLoans() throws Exception {
        return loanDAO.getAllLoans();
    }

    public List<Loan> searchLoan(LocalDateTime date) throws Exception {
        return loanDAO.searchLoan(date);
    }
}
