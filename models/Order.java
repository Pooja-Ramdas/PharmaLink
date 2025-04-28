package com.pharmalink.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {
    private String id;
    private String customerId;
    private String pharmacyId;
    private String medicineId;
    private int quantity;
    private BigDecimal totalPrice;
    private Timestamp createdAt;

    public Order(String id, String customerId, String pharmacyId, String medicineId, int quantity, BigDecimal totalPrice, Timestamp createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPharmacyId() {
        return pharmacyId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Order {" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", pharmacyId='" + pharmacyId + '\'' +
                ", medicineId='" + medicineId + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                '}';
    }
}
