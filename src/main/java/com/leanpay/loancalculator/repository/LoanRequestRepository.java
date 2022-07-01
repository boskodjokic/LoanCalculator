package com.leanpay.loancalculator.repository;

import com.leanpay.loancalculator.entity.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {

    LoanRequest findTopByOrderByIdDesc();
}
