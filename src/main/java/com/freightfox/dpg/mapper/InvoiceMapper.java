package com.freightfox.dpg.mapper;

import com.freightfox.dpg.dto.InvoiceRequest;
import com.freightfox.dpg.dto.InvoiceResponse;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Component
public class InvoiceMapper {

    public Context mapToContext(InvoiceRequest invoiceRequest)
    {
        Context context = new Context();
        Map<String,Object> data = new HashMap<>();
        data.put("seller",invoiceRequest.getSeller());
        data.put("sellerAddress",invoiceRequest.getSellerAddress());
        data.put("sellerGstin",invoiceRequest.getSellerGstin());
        data.put("buyer",invoiceRequest.getBuyer());
        data.put("buyerAddress",invoiceRequest.getBuyerAddress());
        data.put("buyerGstin",invoiceRequest.getBuyerGstin());
        data.put("items",invoiceRequest.getItems());
        context.setVariables(data);
        return context;
    }

    public InvoiceResponse mapToInvoiceResponse(String message, String filename, String filepath)
    {
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setMessage(message);
        invoiceResponse.setFilename(filename);
        invoiceResponse.setFilepath(filepath);
        return invoiceResponse;
    }

}
