package com.leanpay.loancalculator.service;

import com.leanpay.loancalculator.dto.LoanRequestDTO;
import com.leanpay.loancalculator.entity.LoanRequest;
import com.leanpay.loancalculator.entity.LoanSchedule;
import com.leanpay.loancalculator.exceptions.IncompleteDataException;
import com.leanpay.loancalculator.repository.LoanRequestRepository;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanScheduleServiceImpl loanScheduleServiceImpl;

    private final LoanRequestRepository loanRequestRepository;


    public LoanRequestServiceImpl(LoanScheduleServiceImpl loanScheduleServiceImpl, LoanRequestRepository loanRequestRepository) {
        this.loanScheduleServiceImpl = loanScheduleServiceImpl;
        this.loanRequestRepository = loanRequestRepository;
    }

    /**
     * Creating loan schedule plan based on the date parovided in LoanRequestDTO (loan amount, interest rate and loan term)
     * @param loanRequestDTO has all the data needed for further calculation
     * @return list of all loan schedules calculated from the data in loanRequestDTO
     */
    @Override
    public List<LoanSchedule> createNewLoanRequest(LoanRequestDTO loanRequestDTO) {

        if(loanRequestDTO.getLoanAmount() == 0 || loanRequestDTO.getLoanTerm() == 0 || loanRequestDTO.getInterestRate() == 0) {
            throw new IncompleteDataException();
        }

        double loanAmount = loanRequestDTO.getLoanAmount();
        double interestRate = loanRequestDTO.getInterestRate();
        int loanTerm = loanRequestDTO.getLoanTerm();

        double payment = calculatePayment(loanAmount, interestRate, loanTerm);
        double totalPayment = Precision.round(payment * loanTerm, 2);
        double totalInterest = Precision.round(totalPayment - loanAmount, 2);

        LoanRequest loanRequest = LoanRequest.builder()
                .loanAmount(loanAmount)
                .interestRate(interestRate)
                .loanTerm(loanTerm)
                .payment(payment)
                .totalInterest(totalInterest)
                .totalPayment(totalPayment)
                .build();

        loanRequestRepository.save(loanRequest);
        LoanRequest savedLoanRequest = loanRequestRepository.findTopByOrderByIdDesc();

       return loanScheduleServiceImpl.createNewLoanSchedule(loanAmount, interestRate, loanTerm, payment, savedLoanRequest);

    }

    /**
     * Helper method used for calculating the amount of a payment on a monthly basis
     * @param loanAmount total amount requested
     * @param interestRate interest rate requested
     * @param loanTerm number of months for a loan
     * @return monthly payment
     */
    private double calculatePayment(double loanAmount, double interestRate, int loanTerm) {
        double payment;
        double interestRatePerMonth = interestRate / 100 / 12;
        payment = loanAmount * interestRatePerMonth * Math.pow((1 + interestRatePerMonth), loanTerm) / (Math.pow((1 + interestRatePerMonth), loanTerm) - 1);

        return Precision.round(payment, 2);
    }
}
