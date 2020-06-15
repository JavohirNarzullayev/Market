package uz.marokand.market.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uz.marokand.market.entity.Roles;
import uz.marokand.market.entity.Users;
import uz.marokand.market.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
public class UserDetailsServiceImp implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImp.class);
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException,NullPointerException {
        Users users = userRepository.findByUsername(s);
        if (users==null) throw new UsernameNotFoundException("User not found");
        log.info("loadUserByUsername() : {}", s);
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
               return users.getRoles().stream().map(roles ->
                       new SimpleGrantedAuthority(roles.getName())).collect(Collectors.toList());

            }

            @Override
            public String getPassword() {
                return users.getPassword();
            }

            @Override
            public String getUsername() {
                return users.getUsername()  ;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

;
        };
    }
}
