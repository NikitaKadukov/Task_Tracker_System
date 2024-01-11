package com.kadukov.spring.project.spring_project.controller;

import com.kadukov.spring.project.spring_project.entity.User;
import com.kadukov.spring.project.spring_project.service.MailSender;
import com.kadukov.spring.project.spring_project.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuController {

    @Autowired
    UserService userService;

    @Autowired
    private MailSender mailSender;

    @RequestMapping("/")
    public String menu_page(){
        return "menu";
    }

    @RequestMapping("/registry")
    public String registry_page(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registry";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registry";
        }
        else {
            user.setEnabled(true);
            user.setRole("user");
            userService.saveUser(user);
            return "redirect:/";
        }
    }

    @RequestMapping("/forgotPassword")
    public String forgotPassword(){
        return "forgotPasswordView";
    }

    @RequestMapping("/signIN")
    public String signIN(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "signIN";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("task") User user, HttpSession session){
        if(userService.valid(user)){
            User curUser = userService.getUser(user.getUsername());
            session.setAttribute("user", curUser);
            session.setAttribute("sortList", "default");
            session.setAttribute("sortListLast", "default");
            session.setAttribute("darkDesign", false);
            session.setAttribute("paritySort", false);
            return "redirect:/task-tracker/";
        }
        else{
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public String sendEmail(@RequestParam String email, Model model){
        User user = userService.whichEmail(email);
        if(user == null){
            model.addAttribute("info", "Такая почта не зарегистрирована" +
                    " на сайте!");
        }
        else{
            try{
                String message = "Ваши данные:\n" +
                        "\tЛогин: " + user.getUsername() +
                        "\n\tПароль: " + user.getPassword();
                mailSender.send(user.getEmail(), "Восстановление данных", message);
                model.addAttribute("info", "Письмо отправлено успешно!");
            }
            catch (Exception e){
                model.addAttribute("info", "Такая почта не" +
                        "существует или возникла ошибка при отправке на неё!");
            }
        }
        return "info_page";
    }
}
