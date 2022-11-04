package com.backend.company.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    private Long stockId;
    private BigDecimal stockPrice;
    private LocalDate createdDate;
    private String companyCode;

}
