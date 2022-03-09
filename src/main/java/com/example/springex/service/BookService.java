package com.example.springex.service;

import com.example.springex.dto.BookRequest;
import entitiy.Book;
import entitiy.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static entitiy.Book.book;

@Service
public class BookService {
    private Map<Long, Book> books = new HashMap<>();
    private long newBookId =1L;

    public Book insert(BookRequest bookRequest) {
        book = new Book();
        book.setName(bookRequest.getName());
        book.setAuthor(bookRequest.getAuthor());
        book.setPage(bookRequest.getPage());
        book.setId(newBookId);
        newBookId++;
        books.put(book.getId(), book);
        return book;
    }
    
    public void deleteBook(long id) {
        for(int i = 0; i < book.size(); i++){
            Book book = books.get(i);
            if(id == book.getId()){
                book.remove(i);
            }
        }
    }


    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public Book getBook(long id) {
        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(id == book.getId()){
                return book;
            }
        }
    }

    public Book update(long id, BookRequest bookRequest) {
    }
}
