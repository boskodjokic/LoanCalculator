package com.leanpay.loancalculator.controller;

import com.leanpay.loancalculator.dto.LoanRequestDTO;
import com.leanpay.loancalculator.entity.LoanSchedule;
import com.leanpay.loancalculator.exceptions.IncorrectDataException;
import com.leanpay.loancalculator.service.LoanRequestServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Request Controller")
@Tag(name = "Request Controller", description = "This is request controller")
@RestController
@RequestMapping(LoanRequestController.BASE_URL)
public class LoanRequestController {

    public static final String BASE_URL = "/api/requests";

    private LoanRequestServiceImpl loanRequestServiceImpl;

    @Autowired
    public void setInjectedBean(LoanRequestServiceImpl loanRequestServiceImpl) {
        this.loanRequestServiceImpl= loanRequestServiceImpl;
    }

    @ApiOperation(value = "This will create new loan request",
            notes = "Input loan amount, interest rate and loan term (in months)")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<LoanSchedule> createNewLoanRequest(@RequestBody LoanRequestDTO loanRequestDTO) {
        try {
            return loanRequestServiceImpl.createNewLoanRequest(loanRequestDTO);
        } catch (Exception e) {
            throw new IncorrectDataException();
        }

    }
}
