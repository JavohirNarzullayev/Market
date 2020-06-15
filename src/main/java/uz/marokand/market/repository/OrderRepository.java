package uz.marokand.market.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.marokand.market.entity.Order;
import uz.marokand.market.entity.Users;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomer(Users user);

}
