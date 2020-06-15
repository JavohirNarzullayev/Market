package uz.marokand.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.marokand.market.entity.User_Role;
import uz.marokand.market.repository.UserRoleRepository;

import java.util.List;

@Service
public class RolesService {
    @Autowired
    private UserRoleRepository userRoleRepository;


    public List<User_Role> getAllUserRole() {
        return userRoleRepository.findAll();
    }



}
