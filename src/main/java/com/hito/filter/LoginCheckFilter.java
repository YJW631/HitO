package com.hito.filter;

import com.hito.service.UserService;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        String websiteHead=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
        String url = request.getRequestURI().toString();
        if(url.equals("/")){
            response.sendRedirect(websiteHead+"/pages/index.html");
            return;
        }
        if(url.contains("login")||url.contains("register")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.findUserByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        filterChain.doFilter(servletRequest,servletResponse);
                        return;
                    }
                    break;
                }
            }
        }
        response.sendRedirect(websiteHead+"/pages/login.html");
    }

    }
