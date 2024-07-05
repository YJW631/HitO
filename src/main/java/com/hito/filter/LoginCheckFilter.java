package com.hito.filter;

import com.hito.service.AdministratorService;
import com.hito.service.UserService;
import com.hito.vo.Administrator;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Autowired
    private UserService userService;

    @Autowired
    private AdministratorService administratorService;

    /**
     * 对该网站下所用请求进行过滤
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String websiteHead = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String url = request.getRequestURI().toString();
        //直接访问域名+端口号会被重定向到用户主页
        if (url.equals("/")) {
            response.sendRedirect(websiteHead + "/pages/userIndex.html");
            return;
        }
        //对登录请求、注册请求以及静态资源请求发行
        if (url.contains("/pages/") || url.contains("login") || url.contains("register") || url.contains("css")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //对管理端相关请求的过滤
        if (url.contains("administrator")) {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("administrator_token")) {
                        String token = cookie.getValue();
                        Administrator administrator = administratorService.findAdministratorByToken(token);
                        if (administrator != null) {
                            request.getSession().setAttribute("administrator", administrator);
                            filterChain.doFilter(servletRequest, servletResponse);
                            return;
                        }
                    }
                }
            }
            response.sendRedirect(websiteHead + "/pages/administratorLogin.html");
        }
        //对用户端相关请求的过滤
        else if (url.contains("user")) {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        String token = cookie.getValue();
                        User user = userService.findUserByToken(token);
                        if (user != null) {
                            request.getSession().setAttribute("user", user);
                            filterChain.doFilter(servletRequest, servletResponse);
                            return;
                        }
                    }
                }
            }
            response.sendRedirect(websiteHead + "/pages/userLogin.html");
        }
        //对好友系统请求的过滤
        else if (url.contains("friends")) {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        String token = cookie.getValue();
                        User user = userService.findUserByToken(token);
                        if (user != null) {
                            request.getSession().setAttribute("user", user);
                            filterChain.doFilter(servletRequest, servletResponse);
                            return;
                        }
                    }
                }
            }
            response.sendRedirect(websiteHead + "/pages/userLogin.html");
        }
        //对其他请求的处理
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
