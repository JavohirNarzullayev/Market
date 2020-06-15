package uz.marokand.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.marokand.market.entity.Photo;
import uz.marokand.market.entity.Product;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
