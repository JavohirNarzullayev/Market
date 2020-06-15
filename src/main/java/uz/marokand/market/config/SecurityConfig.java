package uz.marokand.market.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import uz.marokand.market.exception.CustomAccessDeniedHandler;
import uz.marokand.market.service.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    @Autowired
    public SecurityConfig(@Lazy @Qualifier(value = "userDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
   }
    private static final String[] pagesFree = {
            "/template",
            //Thymleaf directory
            "/css/**",
            "/js/**",
            "/img/**",
            "/fonts/**",
            "/ico/**",
            "/"
    };

    @Override
    protected void configure( HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/registration").permitAll()
                .antMatchers(pagesFree).permitAll()
                //Доступ только для пользователей с ролью Администратор
                .antMatchers("/admin/**","/admin").access("hasRole('ADMIN')")
//                .antMatchers("/news").hasAuthority("USER")
                //Доступ разрешен всем пользователей
                .antMatchers( "/","/payment","/order/**").access("hasAnyRole('ADMIN','USER')")
                .antMatchers("/invoice").access("hasAnyRole('ADMIN','USER')")
                //Все остальные страницы требуют аутентификаци
                .anyRequest().authenticated()
                .and()
                //Настройка для входа в систему
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .successForwardUrl("/")
                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .httpBasic();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
}

