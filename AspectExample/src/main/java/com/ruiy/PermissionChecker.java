package com.ruiy;

import com.ruiy.annotation.PermissionAnnotation;
import com.ruiy.annotation.UserAnnotation;
import com.ruiy.model.Permission;
import com.ruiy.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class PermissionChecker {

    @Around("execution(* *(..)) && @annotation(permissionAnnotation)")
    public Object checkPermission(ProceedingJoinPoint point, PermissionAnnotation permissionAnnotation) {
        Permission permission = permissionAnnotation.value();
        Signature signature = point.getSignature();
        User user = null;
        if (signature instanceof MethodSignature) {
            MethodSignature ms = (MethodSignature) signature;
            Method method = ms.getMethod();
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            Object[] args = point.getArgs();
            Object userObj = getParameterValue(parameterAnnotations, args, UserAnnotation.class);
            if (userObj != null){
                user = (User) userObj;
            }
        }

        Object result = null;

        if (user == null)
            System.out.println("Can not find user.");
        if (checkPermission(user, permission)) {
            try {
                result = point.proceed();
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No permission allowed.");
        }

        return result;

    }

    private Object getParameterValue(Annotation[][] parameterAnnotations, Object[] args, Class<?> class1) {
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            if (validateParameter(annotations, class1)) {
                return args[i];
            }
        }
        return null;
    }

    private boolean validateParameter(Annotation[] annotations, Class<?> class1) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(class1))
                return true;
        }
        return false;
    }

    public boolean checkPermission(User user, Permission permission) {
        if (user == null)
            return false;

        if (user.getRole().getRole().contains(permission))
            return true;

        return false;
    }
}
