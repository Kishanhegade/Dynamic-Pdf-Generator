package com.freightfox.dpg.controller;

import com.freightfox.dpg.dto.InvoiceRequest;
import com.freightfox.dpg.dto.InvoiceResponse;
import com.freightfox.dpg.service.PdfService;
import com.freightfox.dpg.utility.AppResponseBuilder;
import com.freightfox.dpg.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PdfController {
    private PdfService pdfService;
    private AppResponseBuilder responseBuilder;

    @GetMapping("/api/generate")
    public ResponseEntity<ResponseStructure<InvoiceResponse>> generatePdf(@RequestBody InvoiceRequest invoiceRequest) {
        InvoiceResponse invoiceResponse = pdfService.generatePdf(invoiceRequest);
        return responseBuilder.success(HttpStatus.CREATED, "PDF generated and stored successfully", invoiceResponse);
    }

}
