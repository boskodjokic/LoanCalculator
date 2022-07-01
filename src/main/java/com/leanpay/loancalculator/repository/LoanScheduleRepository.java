package com.leanpay.loancalculator.repository;

import com.leanpay.loancalculator.entity.LoanSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanScheduleRepository extends JpaRepository<LoanSchedule, Long> {

    @Query(value = "SELECT * FROM SCHEDULE WHERE loan_request_id = :loan_request_id", nativeQuery = true)
    List<LoanSchedule> findByOrderByLoanRequestIdDesc(@Param("loan_request_id") Long loanRequestId);

}
