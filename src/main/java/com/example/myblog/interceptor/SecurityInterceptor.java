package com.example.myblog.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.myblog.annotation.NotNeedLogin;
import com.example.myblog.dto.User;
import com.example.myblog.service.UserService;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.sun.jna.platform.win32.OaIdl;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.Date;


public class SecurityInterceptor implements HandlerInterceptor {
    private final static Logger logger = Logger.getLogger(SecurityInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        System.out.println("拦截器进来了");
        response.setContentType("application/json; charset=utf-8");
        String userName;
        String password;
        String token = request.getHeader("token");
//        if(!(handler instanceof MethodHandle)){
//            return true;
//        }
        //response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");

//        HandlerMethod handlerMethod = (HandlerMethod)handler;
//        Method method =handlerMethod.getMethod();
//        if(method.isAnnotationPresent(NotNeedLogin.class)){
//            NotNeedLogin notNeedLogin = method.getAnnotation(NotNeedLogin.class);
//            if(notNeedLogin.require()){
//                return true;
//            }
//        }
        //重点：拦截器会先发送一次option请求，所以如果没有无视这次请求的话就会报错，到不了后台，出现跨域问题
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            System.out.println("Method:OPTIONS");
            return true;
        }
        System.out.println(token);
        if(token == null){
            response.setStatus(500);
            JSONObject data = new JSONObject();
            data.put("msg", "用户未登录！");
            PrintWriter out = response.getWriter();
            out.append(data.toString());
            return false;
        }else{
            try {
                userName = JWT.decode(token).getAudience().get(0);

                System.out.println(userName);
            }catch (JWTDecodeException jwte){
                jwte.printStackTrace();
                response.setStatus(500);
                return false;
            }
            System.out.println(1);

            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userName)).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                response.setStatus(500);
                JSONObject data = new JSONObject();
                data.put("message", "用户不存在！");
                PrintWriter out = response.getWriter();
                out.append(data.toString());
                return false;
            }
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
