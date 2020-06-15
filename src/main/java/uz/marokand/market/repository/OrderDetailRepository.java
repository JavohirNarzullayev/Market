package uz.marokand.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.marokand.market.entity.Order;
import uz.marokand.market.entity.OrderDetails;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails,Long> {
    List<OrderDetails> getByOrder(Order order);

}
