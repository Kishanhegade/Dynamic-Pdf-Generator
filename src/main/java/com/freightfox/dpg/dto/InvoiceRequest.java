package com.freightfox.dpg.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class InvoiceRequest {
    private String seller;
    private String sellerGstin;
    private String sellerAddress;
    private String buyer;
    private String buyerGstin;
    private String buyerAddress;
    private List<Item> items;

    @Data
    public static class Item {
        private String name;
        private String quantity;
        private Double rate;
        private Double amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceRequest that = (InvoiceRequest) o;
        return Objects.equals(seller, that.seller) &&
                Objects.equals(sellerGstin, that.sellerGstin) &&
                Objects.equals(buyer, that.buyer) &&
                Objects.equals(buyerGstin, that.buyerGstin) &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seller, sellerGstin, buyer, buyerGstin, items);
    }
}
