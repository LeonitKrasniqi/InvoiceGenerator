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
public class InvoiceService
{
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

        boolean hasProductWithPriceHigherThan500 = products.stream()
                .anyMatch(product -> product.getPrice() > 500);

        List<Invoice> invoices = new ArrayList<>();
        double total = 0;
        int productCount = 0;
        for (Product product : products) {
            if (product.getPrice() > 500) {
                List<Product> productList = new ArrayList<>();
                productList.add(product);
                invoices.add(Invoice.builder()
                        .products(productList)
                        .SubTotal(product.getPrice())
                        .Discount(product.getDiscount())
                        .VAT(product.getPrice() * invoiceRequestDto.getVAT())
                        .Total(product.getPrice() + (product.getPrice() * invoiceRequestDto.getVAT()))
                        .build());
            } else {
                if (productCount >= 50 || total + (product.getQTY() * product.getPrice()) - product.getDiscount() > 500) {
                    invoices.add(Invoice.builder()
                            .products(new ArrayList<>())
                            .SubTotal(0)
                            .Discount(0)
                            .VAT(0)
                            .Total(0)
                            .build());
                    total = 0;
                    productCount = 0;
                }
                List<Product> productList = invoices.get(invoices.size() - 1).getProducts();
                productList.add(product);
                invoices.get(invoices.size() - 1).setProducts(productList);

                double productTotal = (product.getQTY() * product.getPrice()) - product.getDiscount();
                total += productTotal;
                productCount += product.getQTY();
                invoices.get(invoices.size() - 1).setSubTotal(invoices.get(invoices.size() - 1).getSubTotal() + productTotal);
                invoices.get(invoices.size() - 1).setDiscount(invoices.get(invoices.size() - 1).getDiscount() + product.getDiscount());
                invoices.get(invoices.size() - 1).setVAT(invoices.get(invoices.size() - 1).getSubTotal() * invoiceRequestDto.getVAT());
                invoices.get(invoices.size() - 1).setTotal(invoices.get(invoices.size() - 1).getSubTotal() + invoices.get(invoices.size() - 1).getVAT());
            }
        }

        return invoices;
    }

}
