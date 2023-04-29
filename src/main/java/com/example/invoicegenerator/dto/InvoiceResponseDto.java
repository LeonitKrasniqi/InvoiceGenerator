package com.example.invoicegenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceResponseDto {
private double subtotal;
private double discount;
private double vat;
private  double total;
}
