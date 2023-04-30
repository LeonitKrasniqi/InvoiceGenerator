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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor

public class InvoiceController {

    private final InvoiceService invoiceService;


    @PostMapping("/generate")
    public ResponseEntity<List<InvoiceResponseDto>> createInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto) {
        List<Invoice> invoices = invoiceService.createInvoices(invoiceRequestDto);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();

        for (int i = 0; i < invoices.size(); i++) {
            Invoice invoice = invoices.get(i);
            UUID invoiceId = UUID.randomUUID();

            InvoiceResponseDto invoiceResponseDto = InvoiceResponseDto.builder()
                    .invoiceId(invoiceId)
                    .subtotal(invoice.getSubTotal())
                    .vat(invoice.getVAT())
                    .total(invoice.getTotal())
                    .build();

            invoiceResponseDtos.add(invoiceResponseDto);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceResponseDtos);
    }


}
