package io.github.dev_alan87.sales.service.impl;

public class PurchaseNotFoundException extends RuntimeException {

    public PurchaseNotFoundException() {
        super("Purchase not found.");
    }

}
