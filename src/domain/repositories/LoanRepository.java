package domain.repositories;

import domain.entities.Loan;

import java.util.List;

public interface LoanRepository {
    void addLoan(Loan loan);

    Loan getLoanById(String id);

    List<Loan> getLoansByUserId(String userId);

    List<Loan> getAllLoans();
}