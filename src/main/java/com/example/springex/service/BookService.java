package com.example.springex.service;

import com.example.springex.dto.BookRequest;
import com.example.springex.entitiy.Book;
import com.example.springex.mapper.BookMapper;
import com.example.springex.mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.springex.entitiy.Book.book;


// Spring boot + MyBatis + MySQL
@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private LoanMapper loanMapper;
    public Book insert(BookRequest bookRequest) {
        book = new Book();
        book.setName(bookRequest.getName());
        book.setAuthor(bookRequest.getAuthor());
        book.setPage(bookRequest.getPage());
        book.setActivate(bookRequest.isActivate());
        bookMapper.insertBook(book);

        return book;
    }

    public List<Book> getAllBooks() {
        return bookMapper.findAllBooks();
    }

    public Book getBook(long id) {
        return bookMapper.findBookById(id);
    }

    public boolean isRented(long id) {
        return bookMapper.findRentById(id);
    }

    public void deleteBook(long id) {bookMapper.deactivateBookById(id);}


    public Book update(long id, BookRequest bookRequest) {
        Book book = new Book();
        if (book == null) {
            return null;
        } else {
            book.setAuthor(bookRequest.getAuthor());
            book.setName(bookRequest.getName());
            book.setPage(bookRequest.getPage());
            bookMapper.updateBook(book, id);
            return book;
        }
    }

    public void turnOnBook(long id) {
        bookMapper.turnOnBook(id);
    }

}
