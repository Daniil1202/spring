package ru.gb.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class UserAspect {

    @Around("@annotation(ru.gb.spring.aspect.UserAction)")
    public Object getNode(ProceedingJoinPoint proceedingJoinPoint){  // Извлекаем аргументы метода addTask
        // Изменяем описание задачи
        Object[]args = proceedingJoinPoint.getArgs();
        args[0] = "new description";
        try {
            return proceedingJoinPoint.proceed(args);
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
    }
    @AfterReturning(value = "@annotation(UserAction2)",
            returning = "returnValue")
    public void log(Object returnValue) {
        System.out.println("Task + " + returnValue.toString());
    }
}
