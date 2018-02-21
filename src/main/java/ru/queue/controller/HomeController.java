package ru.queue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.queue.domain.User;
import ru.queue.domain.security.Role;
import ru.queue.repository.RoleRepository;
import ru.queue.service.UserService;
import ru.queue.utility.NameUtil;
import ru.queue.utility.SecurityUtility;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.UUID;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("")
    public String index(Model model, Principal principal){
        if(principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "index";
    }

    @RequestMapping("newUser")
    public String newUser(Model model){
        model.addAttribute("userCreated", false);
        return "newUser";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String newUserPost(
            HttpServletRequest request,
            @ModelAttribute("email") String username,
            @ModelAttribute("password") String password,
            Model model
            ) throws Exception {

        if (userService.findByUsername(username) != null) {
            model.addAttribute("emailExists", true);
        } else {
            User user = new User();
            String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
            user.setPassword(encryptedPassword);
            user.setUsername(username);
            user.setName(NameUtil.generateName());
            Role roleUser = roleRepository.findByName("ROLE_USER");
            user.getRoles().add(roleUser);
            userService.createUser(user);

            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);

            model.addAttribute("userCreated", true);
            model.addAttribute("email", username);
        }
        return "newUser";
    }
    
    @RequestMapping("/profile")
    public String profile(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateProfilePost(HttpServletRequest request, @ModelAttribute("name") String name, @ModelAttribute("newPassword") String password, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        user.setName(name);
        if (!password.isEmpty()) {
            String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
            user.setPassword(encryptedPassword);
        }
        user = userService.save(user);
        model.addAttribute("user", user);
        model.addAttribute("updateSuccessful", true);
        return "profile";
    }

    @RequestMapping(value = "/generateNewName")
    public String generateNewName(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        user.setName(NameUtil.generateName());
        user = userService.save(user);
        return "redirect:profile";
    }



}
