package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.repo.RoleRepository;
import ru.geekbrains.service.UserServiceImpl;
import ru.geekbrains.utils.NotFoundException;

import javax.validation.Valid;

@RequestMapping("/user")
@Controller
public class UserController {

    private final RoleRepository roleRepository;

    private final UserServiceImpl userService;

    @Autowired
    public UserController(RoleRepository roleRepository,
                          UserServiceImpl userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }

    @GetMapping/*("/users")*/
    public String adminUsersPage(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("{id}/edit")
    public String adminEditUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", userService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("roles", roleRepository.findAll());
        return "user";
    }

    @GetMapping("create")
    public String adminCreateUser(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new UserRepr());
        model.addAttribute("roles", roleRepository.findAll());
        return "user";
    }

    @PostMapping
    public String adminUpsertUser(@Valid UserRepr user, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Users");

        if (bindingResult.hasErrors()) {
            return "users";
        }

        userService.save(user);
        return "redirect:/user";
    }

    @DeleteMapping("{id}/delete")
    public String adminDeleteUser(Model model, @PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user";
    }

}

