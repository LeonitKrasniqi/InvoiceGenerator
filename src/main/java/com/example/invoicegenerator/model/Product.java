package com.example.invoicegenerator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Product {
    private String Description;
    private int QTY;
    private double Price;
    private double Discount;
}
