package com.triple.travel.common;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class SessionUtils {

    public static HttpSession getSession(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attr.getRequest().getSession();
    }

    public static String getUserId(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        String userId = session.getAttribute("userId") == null ? "JYPARK" : (String) session.getAttribute("userId");
        return userId ;
    }
}
