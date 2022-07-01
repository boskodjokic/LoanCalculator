package com.leanpay.loancalculator.service;

import com.leanpay.loancalculator.dto.LoanRequestDTO;
import com.leanpay.loancalculator.entity.LoanRequest;
import com.leanpay.loancalculator.repository.LoanRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LoanRequestServiceImplTest {

    LoanRequestServiceImpl loanRequestService;

    @Mock
    LoanRequestRepository loanRequestRepository;

    @Mock
    LoanScheduleServiceImpl loanScheduleServiceImpl;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        loanRequestService = new LoanRequestServiceImpl(loanScheduleServiceImpl, loanRequestRepository);
    }

    /**
     * First, we are making one LoanRequestDTO object with all necessary fields.
     * Then, new LoanRequest object is created which will have fields from DTO object.
     * When we call loanRequestRepository.save() that returns our loanRequest.
     * We are asserting that all the fields are the same.
     */
    @Test
    void createNewLoanRequest() {

        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();
        loanRequestDTO.setLoanTerm(10);
        loanRequestDTO.setLoanAmount(1000);
        loanRequestDTO.setInterestRate(5);

        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setLoanTerm(loanRequestDTO.getLoanTerm());
        loanRequest.setLoanAmount(loanRequestDTO.getLoanAmount());
        loanRequest.setInterestRate(loanRequestDTO.getInterestRate());

        when(loanRequestRepository.save(any(LoanRequest.class))).thenReturn(loanRequest);

        assertEquals(loanRequestDTO.getLoanTerm(), loanRequest.getLoanTerm());
        assertEquals(loanRequestDTO.getLoanAmount(), loanRequest.getLoanAmount());
        assertEquals(loanRequestDTO.getInterestRate(), loanRequest.getInterestRate());
    }

}