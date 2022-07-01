package com.leanpay.loancalculator.dto;

import com.leanpay.loancalculator.entity.LoanRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(description = "Details about the loan schedule")
@Data
public class LoanScheduleDTO {

    @ApiModelProperty(notes = "Unique ID of the loan schedule row", name = "ID", value = "1", hidden = true)
    private Long id;

    @ApiModelProperty(notes = "Month of the payment", name = "1")
    @Column(nullable = false, length = 10)
    private Integer month;

    @ApiModelProperty(notes = "Payment Amount", name = "102.31")
    @Column(nullable = false, length = 6)
    private Double paymentAmount;

    @ApiModelProperty(notes = "Principal Amount", name = "98.14")
    @Column(nullable = false, length = 6)
    private Double principalAmount;

    @ApiModelProperty(notes = "Interest Amount", name = "4.17")
    @Column(nullable = false, length = 6)
    private Double interestAmount;

    @ApiModelProperty(notes = "Balance Owed", name = "901.86")
    @Column(nullable = false, length = 7)
    private Double balanceOwed;

    @ApiModelProperty(notes = "Loan Request", name = "Loan Request")
    private LoanRequest loanRequest;
}
