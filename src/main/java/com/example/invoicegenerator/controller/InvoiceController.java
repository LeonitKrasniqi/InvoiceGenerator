package com.example.invoicegenerator.controller;

import com.example.invoicegenerator.dto.InvoiceRequestDto;
import com.example.invoicegenerator.model.Invoice;
import com.example.invoicegenerator.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor

public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/generate")
    public Invoice generateInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto){
        String description = invoiceRequestDto.getDescription();
        int qty = invoiceRequestDto.getQTY();
        double price = invoiceRequestDto.getPrice();
        double discount = invoiceRequestDto.getDiscount();
        double vat = invoiceRequestDto.getVAT();

        return invoiceService.createInvoice(description,qty,price,discount,vat);
    }

}
