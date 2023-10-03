package com.example.demo.common.intercepter;

import com.auth0.jwt.interfaces.Claim;
import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.entity.User;
import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.enums.RoleType;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.utils.JWTUtil;
import com.example.demo.common.utils.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class RoleInterceptor implements HandlerInterceptor {
    public static final ThreadLocal<UserInfo> userHolder = new ThreadLocal<>();

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        userHolder.set(new UserInfo(new User(1,"test_user","test_logId"), Arrays.asList(RoleType.ADMIN,RoleType.USER,RoleType.ROOT)));
        return true;
        //        RoleRequire roleRequire = ((HandlerMethod) handler).getBeanType().getAnnotation(RoleRequire.class);
//        if (roleRequire == null) {
//            return true;
//        }
//        String token = request.getHeader("token");
//        if (token == null || token.isEmpty()) {
//            throw new GlobalRunTimeException(ErrorEnum.PERMISSION_ERROR, "please login first");
//        }
//        Map<String, Claim> claims = JWTUtil.getClaims(token);
//        String userInfoJson = claims.get("userInfo").asString();
//        UserInfo userInfo = JsonUtil.fromJson(userInfoJson, UserInfo.class);
//        for (RoleType roleType : roleRequire.role()) {
//            if (userInfo.getRoles().contains(roleType)) {
//                userHolder.set(userInfo);
//                return true;
//            }
//        }
//        throw new GlobalRunTimeException(ErrorEnum.PERMISSION_ERROR);
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        userHolder.remove();
    }


}
