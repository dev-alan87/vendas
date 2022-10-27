package io.github.dev_alan87.sales.api.controller.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PurchaseDTO {

	private Integer client;
	private BigDecimal total;
	private List<PurchaseItemDTO> items;
	
	
		
}
