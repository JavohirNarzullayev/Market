package uz.marokand.market.dto;

import uz.marokand.market.entity.Invoice;
import uz.marokand.market.entity.OrderDetails;
import uz.marokand.market.entity.Product;

import java.util.List;

public class InvoiceDto {
    List<Invoice> totalInvoice;
    List<OrderDetails> productsByOrder;

    public InvoiceDto(List<Invoice> totalInvoice, List<OrderDetails> productsByOrder) {
        this.totalInvoice = totalInvoice;
        this.productsByOrder = productsByOrder;
    }

    public InvoiceDto() {
    }

    public List<Invoice> getTotalInvoice() {
        return totalInvoice;
    }

    public void setTotalInvoice(List<Invoice> totalInvoice) {
        this.totalInvoice = totalInvoice;
    }

    public List<OrderDetails> getProductsByOrder() {
        return productsByOrder;
    }

    public void setProductsByOrder(List<OrderDetails> productsByOrder) {
        this.productsByOrder = productsByOrder;
    }
}
