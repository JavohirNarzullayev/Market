package uz.marokand.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.marokand.market.entity.Order;
import uz.marokand.market.entity.OrderDetails;
import uz.marokand.market.repository.OrderDetailRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    public List<OrderDetails> getAllOrders(){
        List<OrderDetails> all = orderDetailRepository.findAll();
        if (all.size()>0){
            return all;
        }
        else return new ArrayList<>();
    }

    public void save(OrderDetails oderDetails) {
        if (oderDetails!=null)
            orderDetailRepository.save(oderDetails);
    }
    public List<OrderDetails> getOrderDetailByOrder(Order order) {
        if (order!=null) {
           return orderDetailRepository.getByOrder(order);
        }
        else return new ArrayList<>();
    }
}
