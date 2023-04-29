package com.example.invoicegenerator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Invoice {
    private String Description;
    private int QTY;
    private double Price;
    private double  Discount;
    private double VAT;
    private double SubTotal;
    private double Total;


}
