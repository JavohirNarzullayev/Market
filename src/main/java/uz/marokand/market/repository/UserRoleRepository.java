package uz.marokand.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.marokand.market.entity.User_Role;

@Repository
public interface UserRoleRepository extends JpaRepository<User_Role,Long> {
    void deleteById(Long id);


    @Modifying
    @Query("update User_Role u set u.name = ?1 where u.id=?2")
     void updateRole(@Param("name") String name, @Param("id") Long id);


}
