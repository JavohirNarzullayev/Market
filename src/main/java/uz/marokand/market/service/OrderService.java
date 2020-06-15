package uz.marokand.market.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.marokand.market.entity.Order;
import uz.marokand.market.entity.OrderDetails;
import uz.marokand.market.entity.Product;
import uz.marokand.market.entity.Users;
import uz.marokand.market.exception.RecordNotFoundException;
import uz.marokand.market.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderService( ProductService productService, OrderRepository orderRepository, OrderDetailService orderDetailService) {
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.orderDetailService = orderDetailService;
    }

    public  void saveOrderDetails(Long id, Integer quantity) throws RecordNotFoundException,NullPointerException{
         //orderDetails
        Product product=productService.getProductById(id);
        OrderDetails oderDetails=new OrderDetails();
        oderDetails.setQuantity(quantity);
        oderDetails.setProduct(product);
        orderDetailService.save(oderDetails);

    }

    public List<Order> getOrderByCustomer(Users users) throws NotFoundException {
        if (users!=null){
        return  orderRepository.findByCustomer(users);
    }
        else throw new NotFoundException("This user not billowing");

}
     public void saveOrder(Order order) throws NotFoundException {
        if (order!=null){
        orderRepository.save(order);
     }
        else throw new NotFoundException("Have problem from order");
}
}
