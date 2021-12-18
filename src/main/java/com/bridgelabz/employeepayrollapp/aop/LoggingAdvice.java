package com.bridgelabz.employeepayrollapp.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// an advice type, which ensures that an advice can run before and after the method execution
@Aspect
@Component
@Slf4j
public class LoggingAdvice {
    /** @purpose: Following is the definition for a PointCut to select
     *  all the methods available. So advice will be called
     *  for all the methods.
     */
    @Pointcut(value = "execution(* com.bridgelabz.employeepayrollapp.*.*.*(..) )")
    public void myPointCut() {
    }

    /**
     * Purpose : This method is created to centralize the logging statement
     * to track the application flow throughout the program
     *
     * @param point : It is an advice type, which ensures that an advice
     *              can run before and after the method execution.
     * @return : The JSON format
     * @throws Throwable : any exception
     */
    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint point) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = point.getSignature().getName();
        String className = point.getTarget().getClass().toString();
        Object[] array = point.getArgs();
        log.info("method invoked" + className + " : " + methodName + "()" + "arguments : "
                + mapper.writeValueAsString(array));
        Object object = point.proceed();
        log.info(className + " : " + methodName + "()" + "Response : "
                + mapper.writeValueAsString(object));
        return object;
    }
}
