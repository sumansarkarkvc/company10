package com.backend.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @Column(name = "company_code")
    @NotEmpty(message = "Company Code cannot be empty")
    @Size(min = 3, max = 8, message = "Company Code must have minimum 3 characters and maximum 8 characters")
    private String companyCode;

    @Column(name = "company_name")
    @NotEmpty(message = "Company Name cannot be empty")
    @Size(min = 2, message = "Company Name must have minimum 2 characters")
    private String companyName;

    @Column(name = "company_ceo")
    @NotEmpty(message = "Company CEO name cannot be empty")
    @Size(min = 2, message = "Company CEO name must have minimum 2 characters")
    private String companyCeo;

    @Column(name = "company_turnover")
    @NotNull(message = "Company turnover cannot be empty")
    @Min(value = 100000000, message = "Company Turnover must be greater than or equal to 10cr")
    private Long companyTurnover;

    @Column(name = "company_website")
    @NotEmpty(message = "Company website cannot be empty")
    @Email(message = "Provide a valid email address")
    private String companyWebsite;

    @Column(name = "company_stock_exchange")
    @NotEmpty(message = "Company stock exchange cannot be empty")
    private String companyStockExchange;
}
