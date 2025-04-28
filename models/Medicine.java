package com.pharmalink.models;

import java.math.BigDecimal;

public class Medicine {
    private String id;
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private int stockQuantity;
    private String category;
    private String description;
    private String imageUrl;
    private String pharmacy_id;

    // Constructor
    public Medicine(String id, String name, String manufacturer, BigDecimal price,
                    int stockQuantity, String category, String description, String imageUrl, String pharmacy_id) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.pharmacy_id = pharmacy_id;
    }

    // Getters and Setters
    // (Thou may use Lombok if thou art weary of repetition)

    public String getId() { return id; }
    public String getName() { return name; }
    public String getManufacturer() { return manufacturer; }
    public BigDecimal getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public String getPharmacyId() {return pharmacy_id; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }


}
