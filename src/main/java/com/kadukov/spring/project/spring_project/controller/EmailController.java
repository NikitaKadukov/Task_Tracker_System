package com.kadukov.spring.project.spring_project.controller;

import com.kadukov.spring.project.spring_project.entity.User;
import com.kadukov.spring.project.spring_project.service.TaskService;
import com.kadukov.spring.project.spring_project.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/editEmail")
    public String editEmail(Model model){
        User curUser = new User();
        model.addAttribute("curUser", curUser);
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        return "edit_email";
    }
    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    public String checkEmail(@Valid @ModelAttribute("curUser") User curUser, BindingResult bindingResult, @RequestParam String newEmail, Model model){
        User user = (User)httpSession.getAttribute("user");
        String regex = "\\w+@\\w+\\.\\w+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(newEmail);
        if(curUser.getPassword().equals(user.getPassword()) && matcher.matches()){
            if(bindingResult.hasErrors()){
                return "edit_email";
            }
            else {
                model.addAttribute("errorEmail", false);
                user.setEmail(newEmail);
                userService.saveUser(user);
                return "redirect:/madeChanges";
            }
        }
        else{
            model.addAttribute("errorEmail", true);
            return "edit_email";
        }
    }

}
