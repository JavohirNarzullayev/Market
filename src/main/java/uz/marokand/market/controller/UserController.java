package uz.marokand.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.marokand.market.dto.UserDto;
import uz.marokand.market.service.UserService;

import javax.validation.Valid;


@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
     public String registration(Model model){
        model.addAttribute("user",new UserDto());

        return "user/registration";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/registration";
        }
            userService.save(user);
            return "redirect:/login";
    }}
