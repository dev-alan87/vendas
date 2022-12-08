package io.github.dev_alan87.sales.api.controller.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.github.dev_alan87.sales.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PurchaseDTO {

    @NotNull(message = "{purchase.client-id.required}")
	private Integer client;
    
    @NotNull(message = "{purchase.total.required}")
	private BigDecimal total;
	
    @NotEmptyList(message = "{purchase.items-list.not-empty}")
	private List<PurchaseItemDTO> items;
}
