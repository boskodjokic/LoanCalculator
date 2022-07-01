package com.leanpay.loancalculator.service;

import com.leanpay.loancalculator.dto.LoanRequestDTO;
import com.leanpay.loancalculator.entity.LoanSchedule;

import java.util.List;

public interface LoanRequestService{

    List<LoanSchedule> createNewLoanRequest(LoanRequestDTO loanRequestDTO);
}
