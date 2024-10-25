package com.freightfox.dpg.controller;

import com.freightfox.dpg.service.PdfService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PdfController {
    private PdfService pdfService;


}
