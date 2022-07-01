package com.leanpay.loancalculator.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@ApiModel(description = "Details about the loan request")
@Data
public class LoanRequestDTO {

    @ApiModelProperty(notes = "Unique ID of the loan request row", name = "ID", value = "1", hidden = true)
    private Long id;

    @ApiModelProperty(notes = "Loan Amount", name = "1000")
    @Column(nullable = false, length = 7)
    private double loanAmount;

    @ApiModelProperty(notes = "Interest Rate", name = "5%")
    @Column(nullable = false, length = 2)
    private double interestRate;

    @ApiModelProperty(notes = "Loan Term", name = "10")
    @Column(nullable = false, length = 3)
    private int loanTerm;

    @JsonIgnore
    @JsonProperty("loanSchedule")
    private List<LoanScheduleDTO> loanSchedule;

}
