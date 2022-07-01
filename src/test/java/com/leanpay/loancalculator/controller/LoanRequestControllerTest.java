package com.leanpay.loancalculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanpay.loancalculator.dto.LoanRequestDTO;
import com.leanpay.loancalculator.entity.LoanSchedule;
import com.leanpay.loancalculator.service.LoanRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoanRequestControllerTest {

    @Mock
    LoanRequestServiceImpl loanRequestService;

    @InjectMocks
    LoanRequestController loanRequestController;

    MockMvc mockMvc;

    /**
     * Necessary set up before all the tests.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(loanRequestController).build();
    }

    /**
     * First, we are making a loanRequestDTO object.
     * After that we are making a loanSchedule object.
     * When we call loanRequestService.createNewLoanRequest() with first object, that returns second object.
     * Than mockMvc calls post on the BASE_URL that accepts JSON format.
     * We expect the status is Created and that paymentAmount is the one we set for our objects.
     */
    @Test
    void createNewLoanRequest() throws Exception {

        LoanRequestDTO loanRequest = new LoanRequestDTO();
        loanRequest.setLoanTerm(10);
        loanRequest.setLoanAmount(1000);
        loanRequest.setInterestRate(5);

        LoanSchedule loanSchedule = LoanSchedule.builder()
                .month(1)
                .paymentAmount(102.31)
                .interestAmount(4.17)
                .principalAmount(98.14)
                .balanceOwed(901.86)
                .build();

        when(loanRequestService.createNewLoanRequest(loanRequest)).thenReturn(Collections.singletonList(loanSchedule));

        mockMvc.perform(post(LoanRequestController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().valueToTree(loanRequest).toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].paymentAmount", equalTo(102.31)));
    }

}