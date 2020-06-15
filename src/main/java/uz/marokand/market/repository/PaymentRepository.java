package uz.marokand.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.marokand.market.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
