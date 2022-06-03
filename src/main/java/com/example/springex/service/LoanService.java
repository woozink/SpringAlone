package com.example.springex.service;

import com.example.springex.dto.LoanRequest;
import com.example.springex.entitiy.Book;
import com.example.springex.entitiy.Loan;
import com.example.springex.entitiy.User;
import com.example.springex.mapper.BookMapper;
import com.example.springex.mapper.LoanMapper;
import com.example.springex.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    //대출일 , 반납 예정일
    public Loan borrowBooks(LoanRequest loanRequest) {
        Book book = bookMapper.findBookById(loanRequest.getBookId());
        User user = userMapper.findUserById(loanRequest.getUserId());

        if (book.isActivate() && !book.isRented() && user.isActivate()) {
            Loan loan = new Loan();
            loan.setReturnDateTime(null);
            loan.setReturned(false);
            loan.setLoanDateTime(LocalDateTime.now());
            loan.setExpireDateTime(LocalDateTime.now().plus(7, ChronoUnit.DAYS));
            loan.setBookId(loanRequest.getBookId());
            loan.setUserId(loanRequest.getUserId());

            book.setRented(true);
            bookMapper.updateBook(book);
            loanMapper.save(loan);

            return loan;
        } else {
            return null;
        }
    }
    //대출 전체 조회
    public List<Loan> getAllLoans() {
        return loanMapper.findAllLoans();
    }

    // 책 반납
    public void returned(int bookId) {
        // loan을 return = true
        // book을 rent =false

        // DB에서 조회 -> 어플리케이션
        Book book = bookMapper.findBookById(bookId);
        // 어플리케이션 상에서 false 로 변경
        book.setRented(false);
        // DB에도 반영
        bookMapper.updateBook(book);

        loanMapper.returnBook(bookId);
    }

    //반납일 연장 - 반납일 7일 연장
    public Loan extend(long id) {
        // 타겟 대출 기록 조회
        // 현재의 expire datetime + 7일로 새로 저장
        Loan loan = loanMapper.findById(id);
        loan.setExpireDateTime(loan.getExpireDateTime().plus(7, ChronoUnit.DAYS));
        loanMapper.extendDate(id, loan);
        return loan;
    }

    public Loan getLoan(long id) {
        return loanMapper.findById(id);
    }
}







