package com.thanhdao.course_online.config.mvc.interceptor;

import com.thanhdao.course_online.utils.constants.RequestConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;
@Slf4j
public class RequestLoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("1");
        String requestId = UUID.randomUUID().toString();
        request.setAttribute(RequestConstants.requestIdAttributeName, requestId);
        request.setAttribute(RequestConstants.requestStartTimeAttributeName, System.currentTimeMillis());
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestId = (String)request.getAttribute(RequestConstants.requestIdAttributeName);
        long executeTime = System.currentTimeMillis() - (Long)request.getAttribute(RequestConstants.requestStartTimeAttributeName);
        log.info("Request { id: {}, method: {}, path: {}, status: {}, time: {}ms }",
                requestId, request.getMethod(),
                request.getRequestURI().replaceAll(request.getContextPath(), ""),
                response.getStatus(), executeTime);
    }
}
