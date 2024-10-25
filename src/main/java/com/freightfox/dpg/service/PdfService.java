package com.freightfox.dpg.service;

import com.freightfox.dpg.dto.InvoiceRequest;
import com.freightfox.dpg.dto.InvoiceResponse;
import com.freightfox.dpg.mapper.InvoiceMapper;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    private InvoiceMapper invoiceMapper;
    private SpringTemplateEngine springTemplateEngine;
    private static final String PDF_DIR = "C:/Users/Kishan/Downloads/";

    public PdfService(InvoiceMapper invoiceMapper,SpringTemplateEngine springTemplateEngine)
    {
        this.invoiceMapper = invoiceMapper;
        this.springTemplateEngine = springTemplateEngine;
    }

    public InvoiceResponse generatePdf(InvoiceRequest invoiceRequest) {
        Context dataContext = invoiceMapper.mapToContext(invoiceRequest);
        String finalHtml = springTemplateEngine.process("invoice", dataContext);

        String fileName = "invoice_" + invoiceRequest.hashCode() + ".pdf";

        String message = htmlToPdf(finalHtml, fileName);
        return invoiceMapper.mapToInvoiceResponse(fileName,PDF_DIR,message);
    }

    private String htmlToPdf(String processedHtml, String fileName) {
        File pdfFile = new File(PDF_DIR + fileName);

        if (!pdfFile.exists()) {
            generateAndStorePdf(processedHtml, pdfFile);
            return "PDF Created";
        }
        return "PDF already Exists!!!";
    }

    private void generateAndStorePdf(String processedHtml, File pdfFile) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
            DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, true, false);
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(defaultFontProvider);

            HtmlConverter.convertToPdf(processedHtml, pdfWriter, converterProperties);

            try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
                fos.write(byteArrayOutputStream.toByteArray());
            }

        } catch (IOException e) {
            throw new RuntimeException("Error generating or saving PDF", e);
        }
    }
}
