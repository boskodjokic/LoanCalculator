package com.leanpay.loancalculator.service;

import com.leanpay.loancalculator.entity.LoanRequest;
import com.leanpay.loancalculator.entity.LoanSchedule;
import com.leanpay.loancalculator.repository.LoanScheduleRepository;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanScheduleServiceImpl implements LoanScheduleService {

    private final LoanScheduleRepository loanScheduleRepository;

    public LoanScheduleServiceImpl(LoanScheduleRepository loanScheduleRepository) {
        this.loanScheduleRepository = loanScheduleRepository;
    }

    /**
     * Creates new loan schedule plan for a loan request
     * @param loanAmount total amount requested
     * @param interestRate interest rate requested
     * @param loanTerm number of months for a loan
     * @param payment monthly payment
     * @param loanRequest loan request from which we are taking id
     * @return list of a loan schedule plan
     */
    @Override
    public List<LoanSchedule> createNewLoanSchedule(double loanAmount, double interestRate, int loanTerm, double payment, LoanRequest loanRequest) {
        double balanceOwed;
        double interestAmount;
        double principalAmount;

        for (int i = 0; i < loanTerm; i++) {

             interestAmount = Precision.round(interestRate / 12 / 100 * loanAmount, 2);
             principalAmount = Precision.round(payment - interestAmount, 2);
            if(loanAmount - principalAmount >= 0) {
                balanceOwed = Precision.round(loanAmount - principalAmount, 2);
            } else {
                balanceOwed = 0;
            }

            LoanSchedule loanSchedule = LoanSchedule.builder()
                    .month(i+1)
                    .paymentAmount(payment)
                    .interestAmount(interestAmount)
                    .principalAmount(principalAmount)
                    .balanceOwed(balanceOwed)
                    .loanRequest(loanRequest)
                    .build();

            loanScheduleRepository.save(loanSchedule);

            loanAmount = balanceOwed;
        }

        return findByOrderByLoanRequestIdDesc(loanRequest.getId());
    }

    /**
     * Helper methods that finds loan schedule based on the id of a loan request
     * @param loanRequestId id of a loan request
     * @return list of loan schedules
     */
    @Override
    public List<LoanSchedule> findByOrderByLoanRequestIdDesc(Long loanRequestId) {
        return loanScheduleRepository.findByOrderByLoanRequestIdDesc(loanRequestId);
    }
}
