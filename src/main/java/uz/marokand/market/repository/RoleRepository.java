package uz.marokand.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.marokand.market.entity.Roles;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long> {
    Roles getByName(String role);


}
