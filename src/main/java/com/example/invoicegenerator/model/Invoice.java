package com.example.invoicegenerator.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class Invoice {

    private List<Product> products;
    private double SubTotal;
    private double Discount;
    private double VAT;
    private double Total;



}


