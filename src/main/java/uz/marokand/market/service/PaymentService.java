package uz.marokand.market.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.marokand.market.config.SecurityUtils;
import uz.marokand.market.entity.*;
import uz.marokand.market.repository.InvoiceRepository;
import uz.marokand.market.repository.PaymentRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final InvoiceService invoiceService;
    private final InvoiceRepository invoiceRepository;
    private final UserService userService;
    private final OrderService orderService;


    @Autowired
    public PaymentService(InvoiceService invoiceService, PaymentRepository paymentRepository, InvoiceRepository invoiceRepository, UserService userService, OrderService orderService) {
        this.invoiceService = invoiceService;
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
        this.userService = userService;
        this.orderService = orderService;

    }

    public void save() throws NotFoundException {
        Users byUserName = userService.getByUserName(SecurityUtils.getCurrentUser().get());
        List<Order> orderByCustomer = orderService.getOrderByCustomer(byUserName);
        Payment payment=new Payment();

        List<Invoice> invoiceList =new ArrayList<>();
        for (Order order:orderByCustomer) {
            invoiceList.add(invoiceRepository.getInvoiceByOrder(order));
        }
        double t=0;
        for (Invoice invoice:invoiceList) {
            Double amount = invoice.getAmount();
            t+=amount;
        }

        payment.setAmount(t);
        payment.setInvoiceList(invoiceList);
        payment.setTime(Timestamp.valueOf(LocalDateTime.now()));
        paymentRepository.save(payment);
    }



    public Payment getPayment(){
        List<Payment> all = paymentRepository.findAll();
        Payment payment=all.get(all.size()-1);
        return payment;
    }
}
