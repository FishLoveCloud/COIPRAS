package edu.seu.interceptor;

import edu.seu.model.HostHolder;
import edu.seu.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;


@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    Visitor visitor;

    /**
     * 需要登录才能访问的接口
     */
    private static List<String> NEED_LOGIN_URIS = Arrays.asList(
        "/updatePassword", "/docOperation"
    );

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (hostHolder.getUser() == visitor) {
            for (String uri: NEED_LOGIN_URIS) {
                if (httpServletRequest.getRequestURI().contains(uri)) {
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login?next="
                            + httpServletRequest.getRequestURI());
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
