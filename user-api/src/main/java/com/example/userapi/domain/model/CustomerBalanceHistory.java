package com.example.userapi.domain.model;


import com.example.userapi.domain.customer.CustomerDto;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBalanceHistory extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    private Customer customer;
    // 변경된돈
    private Integer changeMoney;
    //해당 시점 잔행
    private Integer currentMoney;

    private String fromMessage;

    private String description;


}
