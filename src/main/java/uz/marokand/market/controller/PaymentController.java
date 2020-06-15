package uz.marokand.market.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.marokand.market.entity.Payment;
import uz.marokand.market.service.PaymentService;

import java.util.List;

@Controller
public class PaymentController {
    private final PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment")
    public String pay(Model model) throws NotFoundException {
        paymentService.save();
        Payment payment=paymentService.getPayment();
        model.addAttribute("pay",payment);
        return "client/payment";
    }
}
