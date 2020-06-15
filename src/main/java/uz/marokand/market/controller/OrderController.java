package uz.marokand.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.marokand.market.exception.RecordNotFoundException;
import uz.marokand.market.service.OrderService;

@Controller
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/{id}")
    public String addToOrder(@PathVariable("id")Long id, @RequestParam("quantity")Integer quantity, Model model) throws RecordNotFoundException {
        model.addAttribute("id",id);
        orderService.saveOrderDetails(id,quantity);
        return "redirect:/";
    }
}
