package uz.marokand.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.marokand.market.entity.Invoice;
import uz.marokand.market.entity.Order;
import uz.marokand.market.entity.Users;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
     List<Invoice> findByOrder_Customer(Users users);
     Invoice getInvoiceByOrder(Order order);
}
