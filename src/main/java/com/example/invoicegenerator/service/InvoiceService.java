package com.example.invoicegenerator.service;

import com.example.invoicegenerator.dto.InvoiceRequestDto;
import com.example.invoicegenerator.dto.ProductRequestDto;
import com.example.invoicegenerator.model.Invoice;
import com.example.invoicegenerator.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class InvoiceService
{
public Invoice createInvoice(InvoiceRequestDto invoiceRequestDto){
    List<ProductRequestDto> productRequestDtos = invoiceRequestDto.getProducts();

List<Product> products = productRequestDtos.stream()
        .map(productRequestDto ->
                Product.builder()
                        .Description(productRequestDto.getDescription())
                        .QTY(productRequestDto.getQTY())
                        .Price(productRequestDto.getPrice())
                        .Discount(productRequestDto.getDiscount())
                        .build())
        .collect(Collectors.toList());

    double subtotal = products.stream()
            .mapToDouble(product -> (product.getQTY() * product.getPrice()) - product.getDiscount())
            .sum();
    double discount = products.stream()
            .mapToDouble(Product::getDiscount)
            .sum();
    double vat = subtotal * invoiceRequestDto.getVAT();
    double total = subtotal + vat;

    return Invoice.builder()
            .products(products)
            .SubTotal(subtotal)
            .Discount(discount)
            .VAT(vat)
            .Total(total)
            .build();


}

}
