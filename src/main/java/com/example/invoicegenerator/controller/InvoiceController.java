package com.example.invoicegenerator.controller;

import com.example.invoicegenerator.dto.InvoiceRequestDto;
import com.example.invoicegenerator.dto.InvoiceResponseDto;
import com.example.invoicegenerator.model.Invoice;
import com.example.invoicegenerator.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public ResponseEntity<InvoiceResponseDto> createInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto){
        Invoice invoice = invoiceService.createInvoice(invoiceRequestDto);

        InvoiceResponseDto invoiceResponseDto = InvoiceResponseDto.builder()
                .subtotal(invoice.getSubTotal())
                .discount(invoice.getDiscount())
                .vat(invoice.getVAT())
                .total(invoice.getTotal())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceResponseDto);
    }


}
