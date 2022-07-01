package com.leanpay.loancalculator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "REQUEST")
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 7)
    private Double loanAmount;

    @Column(nullable = false, length = 2)
    private Double interestRate;

    @Column(nullable = false, length = 3)
    private Integer loanTerm;

    @Column(nullable = false, length = 7)
    private Double payment;

    @Column(nullable = false, length = 7)
    private Double totalPayment;

    @Column(nullable = false, length = 7)
    private Double totalInterest;

    @JsonIgnore
    @OneToMany(cascade =
            {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE, CascadeType.REFRESH},
            mappedBy = "loanRequest", fetch = FetchType.EAGER)
    private List<LoanSchedule> loanSchedule;

}
