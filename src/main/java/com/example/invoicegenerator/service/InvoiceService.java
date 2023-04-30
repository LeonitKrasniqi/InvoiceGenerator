package com.example.invoicegenerator.service;

import com.example.invoicegenerator.dto.InvoiceRequestDto;
import com.example.invoicegenerator.dto.ProductRequestDto;
import com.example.invoicegenerator.model.Invoice;
import com.example.invoicegenerator.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class InvoiceService {
    public List<Invoice> createInvoices(InvoiceRequestDto invoiceRequestDto) {
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

        List<Invoice> invoices = new ArrayList<>();
        double total = 0;
        int productCount = 0;
        Invoice currentInvoice = null;
        for (Product product : products) {
            double productTotal = (product.getQTY() * product.getPrice());
            if (currentInvoice == null || (total + productTotal) * (1 + invoiceRequestDto.getVAT()) > 500) {
                currentInvoice = Invoice.builder()
                        .products(new ArrayList<>())
                        .SubTotal(0)
                        .Discount(0)
                        .VAT(0)
                        .Total(0)
                        .build();
                invoices.add(currentInvoice);
                total = 0;
                productCount =0;
            }
            List<Product> productList = currentInvoice.getProducts();
            productList.add(product);
            currentInvoice.setProducts(productList);

            total += productTotal;
            productCount += product.getQTY();
            currentInvoice.setSubTotal(currentInvoice.getSubTotal() + productTotal);
            currentInvoice.setDiscount(currentInvoice.getDiscount() + product.getDiscount());
            currentInvoice.setVAT(currentInvoice.getSubTotal() * invoiceRequestDto.getVAT());
            currentInvoice.setTotal(currentInvoice.getSubTotal() + currentInvoice.getVAT());
        }

        return invoices;
    }
}
