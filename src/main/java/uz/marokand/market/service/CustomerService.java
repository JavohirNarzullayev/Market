package uz.marokand.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.marokand.market.entity.Users;
import uz.marokand.market.exception.RecordNotFoundException;
import uz.marokand.market.repository.UserRepository;
import uz.marokand.market.repository.UserRoleRepository;

import java.util.*;

@Service
public class CustomerService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    @Autowired
    public CustomerService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public Users save(Users users) {
        return userRepository.save(users);
    }
    public List<Users> getAllCustomer() {
        List<Users>customers=userRepository.findAll();
        if (customers.size()>0)return customers;
        else return new ArrayList<>();
    }

    public void update(Users newCustomer){
            Optional<Users> updateCustomer = userRepository.findById(newCustomer.getId());
            if (updateCustomer.isPresent()){
                Users customer=updateCustomer.get();
                customer.setName(newCustomer.getName());
                customer.setCountry(newCustomer.getCountry());
                customer.setPhone(newCustomer.getPhone());
                customer.setEmail(newCustomer.getEmail());
                customer.setUsername(newCustomer.getUsername());
                userRepository.save(customer);
            }
            else {
                userRepository.save(newCustomer);

            }
        }


    public void deleteEmployeeById(Long id) throws RecordNotFoundException {
        Optional<Users> employee = userRepository.findById(id);

        if(employee.isPresent()) {
            userRoleRepository.deleteById(id);
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }




    public List<Users> getCustomerByName(String name){
        List<Users> customers;
        if (name!= null && !name.isEmpty()){
            customers=userRepository.findByNameContaining(name);
        }
        else {
            customers=userRepository.findAll();
        }
        return customers;
    }

    public Users getCustomerById(Long id) throws RecordNotFoundException {
        Optional<Users> employee = userRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
