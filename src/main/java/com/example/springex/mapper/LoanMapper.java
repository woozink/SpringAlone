package com.example.springex.mapper;

import com.example.springex.entitiy.Loan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoanMapper {
    Loan findById(@Param("id") long id);
    void extendDate(@Param("id") long id, @Param(("loan")) Loan loan);
    void returnBook(@Param("id")long id);
    void save(@Param("loan") Loan loan);
}
