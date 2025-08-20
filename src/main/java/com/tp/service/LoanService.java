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

    /*
    public void DeleteLoan(String loan_id) throws Exception {
        loanDAO.DeleteLoan(loan_id);
    }
    */

    public List<Loan> getAllLoansByUser(String user_id) throws Exception {
        return loanDAO.getAllLoansByUser(user_id);
    }

    public List<Loan> getAllLoans() throws Exception {
        return loanDAO.getAllLoans();
    }

    public List<Loan> findByDate(LocalDateTime date) throws Exception {
        return loanDAO.findByDate(date);
    }

    public List<Loan> findByUsername(String user_name) throws Exception {
        return loanDAO.findByUsername(user_name);
    }

    public List<Loan> findByBooktitle(String book_title) throws Exception {
        return loanDAO.findByBooktile(book_title);
    }

    public List<Loan> findByDateAndUser(LocalDateTime date , String user_id) throws Exception {
        return loanDAO.findByDateAndByUser(date , user_id);
    }

    public List<Loan> findByBooktitleAndUser(String book_title , String user_id) throws Exception {
        return loanDAO.findByBooktitleAndByUser(book_title , user_id);
    }
}
