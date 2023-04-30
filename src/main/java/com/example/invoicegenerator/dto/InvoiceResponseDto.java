package com.example.invoicegenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceResponseDto {
    private UUID invoiceId;
private double subtotal;
private double vat;
private  double total;
}
