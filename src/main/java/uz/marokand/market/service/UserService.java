package uz.marokand.market.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.marokand.market.dto.UserDto;
import uz.marokand.market.entity.Roles;
import uz.marokand.market.entity.Users;
import uz.marokand.market.repository.RoleRepository;
import uz.marokand.market.repository.UserRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }


    public void save(@Valid UserDto users)  {
            Set<Roles> roles=new HashSet<>();
            roles.add(roleRepository.getByName("ROLE_USER"));
            Users userInfo=new Users(users.getUsername(),passwordEncoder.encode(users.getPassword()),
                                     users.getEmail(),true,users.getFirstname(),
                                     users.getPhone(),users.getCountry(),roles);
            if (userInfo!=null) {
                userRepository.save(userInfo);
            }else {
                throw new UsernameNotFoundException("Error in form");
            }
    }

    public Users getByUserName(String username) {
        Users byUsername = userRepository.findByUsername(username);
        return byUsername;
    }

    public boolean isUserAlreadyPresent(String username){
        return userRepository.existsByUsername(username);
    }
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
