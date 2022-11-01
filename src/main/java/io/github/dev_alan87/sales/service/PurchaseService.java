package io.github.dev_alan87.sales.service;

import java.util.Optional;

import io.github.dev_alan87.sales.api.controller.dto.PurchaseDTO;
import io.github.dev_alan87.sales.domain.entity.Purchase;
import io.github.dev_alan87.sales.domain.entity.enums.PurchaseStatus;

public interface PurchaseService {

    Purchase save(PurchaseDTO dto);
    Optional<Purchase> getPurchaseInfo(Integer id);
    void updateStatus(Integer id, PurchaseStatus status);

}
