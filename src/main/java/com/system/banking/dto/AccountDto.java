package com.system.banking.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
}
