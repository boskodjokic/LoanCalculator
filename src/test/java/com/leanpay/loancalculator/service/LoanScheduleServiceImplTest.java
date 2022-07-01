package com.leanpay.loancalculator.service;

import com.leanpay.loancalculator.dto.LoanScheduleDTO;
import com.leanpay.loancalculator.entity.LoanSchedule;
import com.leanpay.loancalculator.repository.LoanScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LoanScheduleServiceImplTest {

    LoanScheduleServiceImpl loanScheduleService;

    @Mock
    LoanScheduleRepository loanScheduleRepository;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        loanScheduleService = new LoanScheduleServiceImpl(loanScheduleRepository);
    }

    /**
     * First, we are making one LoanScheduleDTO object with all necessary fields.
     * Then, new LoanSchedule object is created which will have fields from DTO object.
     * When we call loanScheduleRepository.save() that returns our loanSchedule.
     * We are asserting that all the fields are the same.
     */
    @Test
    void createNewLoanSchedule(){
        LoanScheduleDTO loanScheduleDTO = new LoanScheduleDTO();
        loanScheduleDTO.setMonth(1);
        loanScheduleDTO.setPaymentAmount(102.31);
        loanScheduleDTO.setPrincipalAmount(98.14);
        loanScheduleDTO.setInterestAmount(4.17);
        loanScheduleDTO.setBalanceOwed(901.86);

        LoanSchedule loanSchedule = new LoanSchedule();
        loanSchedule.setMonth(loanScheduleDTO.getMonth());
        loanSchedule.setPaymentAmount(loanScheduleDTO.getPaymentAmount());
        loanSchedule.setPrincipalAmount(loanScheduleDTO.getPrincipalAmount());
        loanSchedule.setInterestAmount(loanScheduleDTO.getInterestAmount());
        loanSchedule.setBalanceOwed(loanScheduleDTO.getBalanceOwed());

        when(loanScheduleRepository.save(any(LoanSchedule.class))).thenReturn(loanSchedule);

        assertEquals(loanScheduleDTO.getMonth(), loanSchedule.getMonth());
        assertEquals(loanScheduleDTO.getPaymentAmount(), loanSchedule.getPaymentAmount());
        assertEquals(loanScheduleDTO.getPrincipalAmount(), loanSchedule.getPrincipalAmount());
        assertEquals(loanScheduleDTO.getInterestAmount(), loanSchedule.getInterestAmount());
        assertEquals(loanScheduleDTO.getBalanceOwed(), loanSchedule.getBalanceOwed());
    }

}