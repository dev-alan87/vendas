package io.github.dev_alan87.sales.api.controller.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PurchaseInfoDTO {
    
    private Integer id;
    private String purchaseDate;
    private String clientName;
    private String cpf;
    private BigDecimal total;
    private String status;
    List<PurchaseItemInfoDTO> items;

}
