package ru.sapronov.spring.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.sapronov.spring.boot.dto.UserDto;
import ru.sapronov.spring.boot.models.User;
import ru.sapronov.spring.boot.services.RoleService;
import ru.sapronov.spring.boot.services.UserService;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Ivan Sapronov on 22.02.2021
 * @project spring-boot-bootstrap
 */
@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("user")
    public String userPage(Authentication authentication, ModelMap model) {
        User user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "admin")
    public String adminPage(@ModelAttribute("userDto") UserDto dto,
                             Authentication authentication, ModelMap model) {
        User admin = userService.getUserByUsername(authentication.getName());
        List<User> users = userService.index();
        model.addAttribute("admin", admin);
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping(value = "admin/add")
    public String createUser(@ModelAttribute("userDto") UserDto dto) {
        userService.save(dtoToUser(dto));
        return "redirect:/admin";
    }

    @PostMapping(value = "admin/edit")
    public String editUser(@ModelAttribute("userDto") UserDto dto) {
        userService.update(dtoToUser(dto));
        return "redirect:/admin";
    }

    @PostMapping(value = "admin/delete")
    public String deleteUser(@ModelAttribute("userDto") UserDto dto) {
        userService.delete(dto.getId());
        return "redirect:/admin";
    }

    private User dtoToUser(UserDto dto) {
        User user = new User(dto);
        user.setRoles(dto.getRoles().stream()
                .map(r -> roleService.getRoleByName(r)).collect(Collectors.toSet()));
        return user;
    }
}