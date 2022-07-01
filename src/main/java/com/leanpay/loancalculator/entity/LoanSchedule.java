package com.leanpay.loancalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "SCHEDULE")
public class LoanSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private Integer month;

    @Column(nullable = false, length = 6)
    private Double paymentAmount;

    @Column(nullable = false, length = 6)
    private Double principalAmount;

    @Column(nullable = false, length = 6)
    private Double interestAmount;

    @Column(nullable = false, length = 7)
    private Double balanceOwed;

    @ManyToOne
    private LoanRequest loanRequest;
}
