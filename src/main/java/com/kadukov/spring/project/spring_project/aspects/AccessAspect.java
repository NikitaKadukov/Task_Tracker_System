package com.kadukov.spring.project.spring_project.aspects;

import javax.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccessAspect {
    @Autowired
    HttpSession httpSession;
    @Pointcut("execution(* com.kadukov.spring.project.spring_project.controller.*Controller.*(..))")
    private void allControllerMethods(){}

    @Pointcut("execution(* com.kadukov.spring.project.spring_project.controller.MenuController.*(..))")
    private void menuMethods(){}

    @Pointcut("execution(* com.kadukov.spring.project.spring_project.controller.RESTController.*(..))")
    private void restMethods(){}

    @Pointcut("allControllerMethods() && !menuMethods() && !restMethods()")
    private void methodsForAccess(){}

    @Around("methodsForAccess()")
    public Object checkAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        if(httpSession.getAttribute("user")==null){
            return "redirect:/";
        }
        return proceedingJoinPoint.proceed();
    }


}
