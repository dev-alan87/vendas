package io.github.dev_alan87.sales.service;

import io.github.dev_alan87.sales.api.controller.dto.PurchaseDTO;
import io.github.dev_alan87.sales.domain.entity.Purchase;

public interface PurchaseService {

    Purchase save(PurchaseDTO dto);

}
