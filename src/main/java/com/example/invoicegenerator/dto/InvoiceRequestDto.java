package com.example.invoicegenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceRequestDto {
    private List<ProductRequestDto> products;
    private Double VAT;

}
