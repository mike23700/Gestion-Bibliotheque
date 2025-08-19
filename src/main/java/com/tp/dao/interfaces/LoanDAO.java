package com.tp.dao.interfaces;

import com.tp.model.Loan;

import java.time.LocalDateTime;
import java.util.List;

public interface LoanDAO {
    void AddLoan(Loan loan) throws Exception;
    void DeleteLoan(String loan_id) throws Exception;
    List<Loan> getAllLoans() throws Exception;
    List<Loan> searchLoan(LocalDateTime date) throws Exception;
}
