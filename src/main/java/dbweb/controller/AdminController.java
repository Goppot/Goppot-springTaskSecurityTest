package dbweb.controller;

import dbweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "/all-users";
    }

    @GetMapping("user/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/user";
    }

    @GetMapping("/create-user")
    public String createUserForm() {
        return "/create-user";
    }

    @PostMapping("/create-user")
    public String createUser(@RequestParam String name,
                             @RequestParam int age,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String role) {
        userService.addUser(name, age, email, password, role);
        return "redirect:/admin";
    }

    @GetMapping("update-user/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/update-user";
    }

    @PostMapping("/update-user")
    public String updateUser(@RequestParam int id,
                             @RequestParam String name,
                             @RequestParam int age,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String role) {
        userService.updateUser(id, name, age, email, password, role);
        return "redirect:/admin";
    }

    @PostMapping("delete-user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}
