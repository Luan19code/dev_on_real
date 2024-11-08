package application;

import domain.entities.Book;
import domain.entities.Loan;
import domain.entities.User;
import domain.repositories.LoanRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class LoanService {
    private final LoanRepository loanRepository;
    private static final int MAX_LOANS = 999999;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void loanBook(Book book, User user) throws Exception {
        List<Loan>  LoansByThisUser = loanRepository.getLoansByUserId(user.getId());
        double totalFine = 0;

        for (Loan loan : LoansByThisUser) {
            totalFine += loan.getFine();
        }

        if (totalFine != 0) {
            String loanId = UUID.randomUUID().toString();
            Loan loan = new Loan(loanId, book, user, LocalDate.now());
            loanRepository.addLoan(loan);
            user.addLoan(loan);
        } else {
            throw new Exception("this User have fines to pay");
        }
    }

    public void returnBook(String loanId) {
        Loan loan = loanRepository.getLoanById(loanId);
        if (loan != null) {
            loan.returnBook(LocalDate.now());
        }
    }

    public List<Loan> getLoansByUserId(String userId) {
        return loanRepository.getLoansByUserId(userId);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.getAllLoans();
    }
}