package com.hnasc.orderproducts.models.enums;

public enum OrderStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    PAID("PAID"),
    SHIPPED("SHIPPED"),
    DELIVERED("DELIVERED"),
    CANCELED("CANCELED");

    private String status;
    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
