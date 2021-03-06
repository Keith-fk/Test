package com.jaeden.pin.service.annotation;

import com.jaeden.pin.domain.request.BaseRequest;
import com.jaeden.pin.service.CacheService;
import com.jaeden.pin.service.util.ResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 类LoginCheckAopProcessor.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-27
 */
@Component
@Aspect
public class ParamCheckProcessor {
    @Autowired
    private CacheService cacheService;

    @Around("@annotation(com.jaeden.pin.service.annotation.ParamCheck)")
    public Object checkAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object target = joinPoint.getTarget();
        Method[] methods = target.getClass().getDeclaredMethods();
        Class returnType = null;
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                returnType = m.getReturnType();
                break;
            }
        }
        if (returnType == null) {
            return joinPoint.proceed();
        }
        Object[] objects = joinPoint.getArgs();
        for (Object o : objects) {
            if (o instanceof BaseRequest) {
                BaseRequest baseRequest = (BaseRequest) o;
                Integer userId = cacheService.getUserId(baseRequest.getToken());
                if (userId != null) {
                    return joinPoint.proceed();
                } else {
                    return ResponseUtil.errorResponse("LOGIN");
                }
            }
            if (o instanceof BindingResult && ((BindingResult) o).hasErrors()) {
                return ResponseUtil.errorResponse(error((BindingResult) o));
            }
        }
        return joinPoint.proceed();
    }

    private String error(BindingResult result) {
        StringBuffer sb = new StringBuffer();
        List<ObjectError> list = result.getAllErrors();
        for (ObjectError error : list) {
            String msg = error.getDefaultMessage();
            sb.append(msg);
        }
        return sb.toString();
    }
}