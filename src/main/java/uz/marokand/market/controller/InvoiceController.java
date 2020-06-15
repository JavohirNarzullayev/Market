package uz.marokand.market.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.marokand.market.dto.InvoiceDto;
import uz.marokand.market.entity.Invoice;
import uz.marokand.market.entity.OrderDetails;
import uz.marokand.market.service.InvoiceService;

import java.util.List;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoice")
    public String invoice(Model model) throws NotFoundException {
        invoiceService.confirmOrders();
        List<Invoice> allInvoice = invoiceService.getAllInvoice();
        List<OrderDetails> productByOrder = invoiceService.getProductByOrder();
        InvoiceDto invoiceDto=new InvoiceDto();
        invoiceDto.setProductsByOrder(productByOrder);
        invoiceDto.setTotalInvoice(allInvoice);
        model.addAttribute("productInvoice",invoiceDto);
        return "client/invoice";
    }

}
