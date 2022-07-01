package com.leanpay.loancalculator.service;

import com.leanpay.loancalculator.entity.LoanRequest;
import com.leanpay.loancalculator.entity.LoanSchedule;

import java.util.List;

public interface LoanScheduleService {

    List<LoanSchedule> createNewLoanSchedule(double loanAmount, double interestRate, int loanTerm, double payment, LoanRequest loanRequest);

    List<LoanSchedule> findByOrderByLoanRequestIdDesc(Long loanRequestId);
}
