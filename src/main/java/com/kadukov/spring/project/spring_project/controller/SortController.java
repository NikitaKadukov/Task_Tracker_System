package com.kadukov.spring.project.spring_project.controller;

import com.kadukov.spring.project.spring_project.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sorting")
public class SortController {
    @Autowired
    TaskService taskService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/sortTasks/{curSorted}")
    public String sortList(@PathVariable String curSorted){
        String lastSorted = (String) httpSession.getAttribute("sortListLast");
        boolean paritySort = (boolean) httpSession.getAttribute("paritySort");
        if(curSorted.equals(lastSorted) && !curSorted.equals("default")){
            paritySort = !paritySort;
        }
        else{
            paritySort = false;
        }
        httpSession.removeAttribute("sortListLast");
        httpSession.setAttribute("sortListLast", curSorted);
        httpSession.removeAttribute("sortList");
        httpSession.setAttribute("sortList", curSorted);
        httpSession.removeAttribute("paritySort");
        httpSession.setAttribute("paritySort", paritySort);
        return "redirect:/task-tracker/";
    }
}
