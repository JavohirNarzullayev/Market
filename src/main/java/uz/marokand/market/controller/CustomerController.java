package uz.marokand.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.marokand.market.entity.Users;
import uz.marokand.market.exception.RecordNotFoundException;
import uz.marokand.market.service.CustomerService;
import uz.marokand.market.service.RolesService;
import uz.marokand.market.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class CustomerController {
    private final CustomerService customerService;
    private final RolesService rolesService;
    private final UserService userService;
    @Autowired
    public CustomerController(CustomerService customerService, RolesService rolesService, UserService userService) {
        this.customerService = customerService;
        this.rolesService = rolesService;
        this.userService = userService;

    }
//complete
    @GetMapping(path = {"/customer",""})
    public String customer(Model model){
        model.addAttribute("user",userService.getAllUsers());
        return "user/list-customer";
    }

//complete
    @GetMapping("/customer/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws RecordNotFoundException{
        customerService.deleteEmployeeById(id);
        return "redirect:/admin/customer";
    };

//    complete
    @RequestMapping(value = "/customer/search",method = RequestMethod.POST)
    public String search(Model model,@RequestParam String name){
        List<Users> customer = customerService.getCustomerByName(name);
        model.addAttribute("user",customer);
        return "user/list-customer";
    }

    @GetMapping(path = {"/customer/edit/{id}","/customer/edit"})
    public String editCustomer(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        Users users=customerService.getCustomerById(id.get());
        model.addAttribute("editCustomer",users);
        model.addAttribute("roles",rolesService.getAllUserRole());
        return "user/add-edit-customer";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute Users users,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "user/add-edit-customer";
        }
        customerService.update(users);
        return "redirect:/admin/customer";
    }
}

