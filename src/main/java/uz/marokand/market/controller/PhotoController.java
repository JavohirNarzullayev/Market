package uz.marokand.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.marokand.market.config.SecurityUtils;
import uz.marokand.market.dto.OrderDto;
import uz.marokand.market.entity.OrderDetails;
import uz.marokand.market.entity.Product;
import uz.marokand.market.repository.OrderDetailRepository;
import uz.marokand.market.service.PhotoService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PhotoController {
    final
    OrderDetailRepository orderDetailRepository;
    final PhotoService photoService;
    @Autowired
    public PhotoController(PhotoService photoService, OrderDetailRepository orderDetailRepository) {
        this.photoService = photoService;
        this.orderDetailRepository = orderDetailRepository;
    }
    @GetMapping
    public String allProductPhoto(Model model) {
        List<Product> allPhotos = photoService.getAllPhotos();
        List<OrderDetails> orderDetailsList=orderDetailRepository.findAll();
        Optional<String> user= SecurityUtils.getCurrentUser();
        model.addAttribute("user",user.get());
        model.addAttribute("photos",new OrderDto(allPhotos));

        return "client/index";
    }
}
