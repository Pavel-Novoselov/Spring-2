package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.model.Role;
import ru.geekbrains.repo.RoleRepository;
import ru.geekbrains.service.RoleService;

import java.util.Optional;

@Controller
@RequestMapping("/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String roleList(Model model,
                               @RequestParam(name = "partName", required = false, defaultValue = "") String partName,
                               @RequestParam(name = "page") Optional<Integer> page,
                               @RequestParam(name = "size") Optional<Integer> size) {
        logger.info("Roles list");
        Page<Role> rolePage;
        System.out.println("partName!=null");
        rolePage = roleService.filterName(partName,
                PageRequest.of(page.orElse(1) - 1, size.orElse(5)));

        model.addAttribute("rolePage", rolePage);
        model.addAttribute("prevPageNumber", rolePage.hasPrevious() ? rolePage.previousPageable().getPageNumber() + 1 : -1);
        model.addAttribute("nextPageNumber", rolePage.hasNext() ? rolePage.nextPageable().getPageNumber() + 1 : -1);

        return "roles";
    }

    @GetMapping("new")
    public String createRole(Model model) {
        logger.info("Create role form");
        System.out.println("1 step");
        model.addAttribute("role", new Role());
        return "role";
    }

    @PostMapping
    public String saveRole(Role role) {
        logger.info("Save role method");

        roleService.save(role);
        return "redirect:/role";
    }


    @GetMapping("edit")
    public String editRole(@RequestParam long id, Model model){

        model.addAttribute("role", roleService.findById(id));
        return "role";

    }

    @DeleteMapping
    public String deleteRole(@RequestParam Long id){
        System.out.println("delete");
        roleService.deleteRole(id);
        return "redirect:/role";
    }
}
