package application;

import domain.entities.Book;
import domain.repositories.BookRepository;

import java.util.List;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);
    }

    public Book getBookById(String id) {
        return bookRepository.getBookById(id);
    }

    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }
}
