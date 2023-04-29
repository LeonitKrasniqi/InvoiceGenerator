package com.example.invoicegenerator.service;

import com.example.invoicegenerator.model.Invoice;
import org.springframework.stereotype.Service;


@Service
public class InvoiceService
{
    public Invoice createInvoice(String description, int qty, double price, double discount, double vat) {
        double subTotal = (qty * price) - discount;
        double totalVat = subTotal * vat;
        double total = subTotal + totalVat;

        return Invoice.builder()
                .Description(description)
                .QTY(qty)
                .Price(price)
                .Discount(discount)
                .VAT(vat)
                .SubTotal(subTotal)
                .Total(total)
                .build();
    }

}
