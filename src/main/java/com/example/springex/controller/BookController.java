package com.example.springex.controller;


import com.example.springex.dto.BookRequest;
import com.example.springex.dto.BookResponse;
import com.example.springex.dto.UserRequest;
import com.example.springex.dto.UserResponse;
import com.example.springex.service.BookService;
import entitiy.Book;
import entitiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class BookController {
    @Autowired
    private BookService bookService;

    //도서등록
    @PostMapping("")
    public ResponseEntity<Book> registraionBook(@RequestBody BookRequest bookRequest){
        Book book = bookService.insert(bookRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
    // 특정 ID의 도서 삭제
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    // 도서 전체 조회
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getALLBook(){
        List<Book> bookList = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(bookList);
    }

    // 특정 Id의 도서 조회
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id){
        Book book = bookService.getBook(id);
        BookResponse userResponse = convert(book);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }


    // 도서 수정(도서명, 저자, 페이지)
    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponse> putBook(@PathVariable long id, @RequestBody BookRequest bookRequest){
        Book book = bookService.update(id, bookRequest);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setName(book.getName());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setPage(book.getPage());

        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }


    private BookResponse convert(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setName(book.getName());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setPage(book.getPage());
    }







}
