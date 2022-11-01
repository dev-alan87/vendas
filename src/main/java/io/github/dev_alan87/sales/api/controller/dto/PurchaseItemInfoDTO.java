package io.github.dev_alan87.sales.api.controller.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PurchaseItemInfoDTO {

    private String productDescription;
    private BigDecimal unitPrice;
    private Integer quantitty;

}
