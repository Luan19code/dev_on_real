package infrastructure.repositories;

import domain.entities.Loan;
import domain.repositories.LoanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryLoanRepository implements LoanRepository {
    private List<Loan> loans = new ArrayList<>();

    @Override
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    @Override
    public Loan getLoanById(String id) {
        return loans.stream()
                .filter(loan -> loan.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Loan> getLoansByUserId(String userId) {
        return loans.stream()
                .filter(loan -> loan.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> getAllLoans() {
        return loans;
    }
}
