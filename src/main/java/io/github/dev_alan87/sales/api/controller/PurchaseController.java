package io.github.dev_alan87.sales.api.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.dev_alan87.sales.api.controller.dto.PurchaseDTO;
import io.github.dev_alan87.sales.api.controller.dto.PurchaseInfoDTO;
import io.github.dev_alan87.sales.api.controller.dto.PurchaseItemInfoDTO;
import io.github.dev_alan87.sales.api.controller.dto.PurchaseStatusDTO;
import io.github.dev_alan87.sales.domain.entity.Purchase;
import io.github.dev_alan87.sales.domain.entity.PurchaseItem;
import io.github.dev_alan87.sales.domain.entity.enums.PurchaseStatus;
import io.github.dev_alan87.sales.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

	private PurchaseService service;

	public PurchaseController(PurchaseService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer save(@RequestBody PurchaseDTO dto) {
		return service.save(dto).getId();
	}

	@PatchMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateStatus(@PathVariable Integer id, @RequestBody PurchaseStatusDTO dto) {
		service.updateStatus(id, PurchaseStatus.valueOf(dto.getStatus()));
	}

	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.FOUND)
	public PurchaseInfoDTO getById(@PathVariable Integer id) {
		return service.getPurchaseInfo(id)
							.map( p ->
								convert(p)
							)
							.orElseThrow( () ->
								new ResponseStatusException(HttpStatus.NOT_FOUND)
							);
	}
	private PurchaseInfoDTO convert(Purchase p) {
		return PurchaseInfoDTO.builder()
							.id(p.getId())
							.purchaseDate(p.getPurchaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))
							.clientName(p.getClient().getName())
							.cpf(p.getClient().getCpf())
							.total(p.getTotal())
							.status(p.getStatus().name())
							.items(convert(p.getItems()))
							.build();
	}

	private List<PurchaseItemInfoDTO> convert(List<PurchaseItem> itens) {
		if(CollectionUtils.isEmpty(itens))
			return Collections.emptyList();

		return itens.stream().map(
			item ->  PurchaseItemInfoDTO.builder()
						.productDescription(item.getProduct().getDescription())
						.unitPrice(item.getProduct().getPrice())
						.quantitty(item.getQty())
						.build()
		).collect(Collectors.toList());
	}


}
