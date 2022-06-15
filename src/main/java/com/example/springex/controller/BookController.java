package com.example.springex.controller;

import com.example.springex.dto.BookRequest;
import com.example.springex.dto.BookResponse;
import com.example.springex.service.BookService;
import com.example.springex.entitiy.Book;
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
    public ResponseEntity<Book> registraionBook(@RequestBody BookRequest bookRequest) {
        Book book = bookService.insert(bookRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    // 도서 전체 조회
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getALLBook() {
        List<Book> bookList = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(bookList);
    }


    // 도서 수정(도서명, 저자, 페이지)
    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponse> putBook(@PathVariable long id, @RequestBody BookRequest bookRequest) {
        Book book = bookService.update(id, bookRequest);
        BookResponse bookResponse = new BookResponse();

        bookResponse.setId(id);
        bookResponse.setName(book.getName());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setPage(book.getPage());

        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }


    // 특정 Id의 도서 조회
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Book book = bookService.getBook(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        BookResponse bookResponse = convert(book);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    // 특정 Id의 반납 여부 조회
    @GetMapping("/books/rented/{id}")
    public ResponseEntity<Boolean> isRented(@PathVariable long id) {
        Boolean book = bookService.isRented(id);
        BookResponse bookResponse = new BookResponse();

        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        bookResponse.isRented();

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    // 특정 ID의 도서 삭제
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // 비활성화된 ID를 다시 활성화로 바꿈
    @PatchMapping("/books/{id}")
    public ResponseEntity<Void> turnOnBook(@PathVariable long id){
        bookService.turnOnBook(id);
        return ResponseEntity.ok().build();
    }

//     input, output
//     Get /books/search - Query Param - 책 목록 응답
//    API를 어떻게 설계할것인가
//     1. HTTP Method (Get? Put? Post? Delete?)
//     2. Request Path (/../../..)
//     3. Request Param (Path Variable / Request Body / Query Param,Query String==@RequestParam)
//     4. Response - 무엇을 응답해줄것인가 ? 내부 구현은 어떻게 할 것인가

//     1. Service (비즈니스로직/핵심로직)
//     2. DB에서 값을 가져오는가? 어떻게 무엇을 가져올 것인가?

    // 책이름으로 책 검색
    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchBook(@RequestParam(value = "name")String name){
        List<Book> bookList = bookService.searchBook(name);
        if (name == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookList);
    }

    // /books/search2/{name}
    @GetMapping("/books/search2")
    public void search2(@RequestParam String name, @RequestParam int age){
        System.out.println(name + " " + age);
    }

    private BookResponse convert(Book book) {
        BookResponse bookResponse = new BookResponse();

        bookResponse.setName(book.getName());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setPage(book.getPage());

        return bookResponse;
    }
}
