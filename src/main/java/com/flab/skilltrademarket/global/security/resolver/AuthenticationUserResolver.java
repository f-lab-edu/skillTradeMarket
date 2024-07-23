package com.flab.skilltrademarket.global.security.resolver;

import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
public class AuthenticationUserResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isAnnotation = parameter.hasParameterAnnotation(AuthenticationUser.class);
        boolean isParameterType =parameter.getParameterType().isNestmateOf(UserDetails.class);

        return isAnnotation && isParameterType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory){
        RequestAttributes requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        Object principal = requestContext.getAttribute(HttpHeaders.AUTHORIZATION, RequestAttributes.SCOPE_REQUEST);

        if (principal == null) {
            throw new ApiException(ExceptionCode.COMMON_SYSTEM_ERROR);
        }
        if (!parameter.getParameterType().isAssignableFrom(principal.getClass())) {
            throw new ApiException(ExceptionCode.COMMON_SYSTEM_ERROR);
        }
        return principal;
    }
}
