package io.github.dev_alan87.sales.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.dev_alan87.sales.api.controller.dto.PurchaseDTO;
import io.github.dev_alan87.sales.api.controller.dto.PurchaseItemDTO;
import io.github.dev_alan87.sales.domain.entity.Purchase;
import io.github.dev_alan87.sales.domain.entity.PurchaseItem;
import io.github.dev_alan87.sales.domain.respository.Clients;
import io.github.dev_alan87.sales.domain.respository.Products;
import io.github.dev_alan87.sales.domain.respository.PurchaseItems;
import io.github.dev_alan87.sales.domain.respository.Purchases;
import io.github.dev_alan87.sales.service.PurchaseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

	private final Purchases repository;
	private final Clients clients;
	private final Products products;
	private final PurchaseItems items;
	
	@Override
	@Transactional
	public Purchase save(PurchaseDTO dto) {
		Purchase purchase = new Purchase();
		purchase.setTotal(dto.getTotal());
		purchase.setPurchaseDate(LocalDate.now());
		purchase.setClient(clients
							.findById(dto.getClient())
							.orElseThrow(() -> 
								new RuleExcepcion("Client not found")
							)
						);
		repository.save(purchase);

		purchase.setItems(this.convertItems(purchase, dto.getItems())); //verificar se vai retornar purchase completo
		items.saveAll(purchase.getItems());

		return purchase;
	}

	private List<PurchaseItem> convertItems(Purchase purchase, List<PurchaseItemDTO> items) {
		if(items.isEmpty())
			throw new RuleExcepcion("Your purchase is empty");

		return items
				.stream()
				.map(dto -> {
					PurchaseItem item = new PurchaseItem();
					item.setPurchase(purchase);
					item.setQty(dto.getQty());
					item.setProduct(products
										.findById(dto.getProduct())
										.orElseThrow(() -> 
											new RuleExcepcion("Invalid code product: "+dto.getProduct())
										)
									);
					return item;
				}).collect(Collectors.toList());
	}
	
}
