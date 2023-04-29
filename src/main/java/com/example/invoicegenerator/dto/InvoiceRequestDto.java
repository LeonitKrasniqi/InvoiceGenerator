package com.example.invoicegenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceRequestDto {
    private String Description;
    private int QTY;
    private double Price;
    private double Discount;
    private double VAT;

}
