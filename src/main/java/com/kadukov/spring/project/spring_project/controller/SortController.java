package com.kadukov.spring.project.spring_project.controller;

import com.kadukov.spring.project.spring_project.entity.Task;
import com.kadukov.spring.project.spring_project.entity.User;
import com.kadukov.spring.project.spring_project.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

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
        boolean chetSort = (boolean) httpSession.getAttribute("chetSort");
        if(curSorted.equals(lastSorted) && !curSorted.equals("default")){
            chetSort = !chetSort;
        }
        else{
            chetSort = false;
        }
        httpSession.removeAttribute("sortListLast");
        httpSession.setAttribute("sortListLast", curSorted);
        httpSession.removeAttribute("sortList");
        httpSession.setAttribute("sortList", curSorted);
        httpSession.removeAttribute("chetSort");
        httpSession.setAttribute("chetSort", chetSort);
        return "redirect:/task-tracker/";
    }
}
