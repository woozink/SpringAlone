package com.example.springex.mapper;

import com.example.springex.entitiy.Book;
import com.example.springex.entitiy.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> findAllBooks();
    Book findBookById(long id);
    Book findBookByNameAndAuthorAndPage(@Param("name") String name,@Param("author") String author, @Param("page") int page);
    void insertBook(@Param("book") Book book);
    void updateBook(Book book);
    void deleteBookById(long id);
}
