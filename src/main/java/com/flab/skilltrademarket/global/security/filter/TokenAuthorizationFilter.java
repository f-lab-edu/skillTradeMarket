package com.flab.skilltrademarket.global.security.filter;

import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.jwt.TokenProvider;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.service.UserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {
    private static final List<String> EXCLUDE_END_POINTS = List.of(
            "/stm/signup",
            "/stm/check-nickName",
            "/stm/check-email",
            "/stm/phone-confirm",
            "/stm/phone-verification",
            "/stm/login",
            "/stm/reissue",
            "/stm/logout"
    );
    private static final String GRANT_TYPE = "Bearer ";
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    private final TokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("requestURI: {}", request.getRequestURI());

        String authorizedHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!isValidToken(authorizedHeader)){
            handleException(response, ExceptionCode.ACCESS_DENIED);
            return;
        }
        String accessToken = authorizedHeader.substring(GRANT_TYPE.length());

        try {
            String username = tokenProvider.getClaimUsername(accessToken);

            UserDetails userDetails = userDetailsService.loadByUsername(username);
            RequestAttributes requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute(HttpHeaders.AUTHORIZATION, userDetails, RequestAttributes.SCOPE_REQUEST);

            filterChain.doFilter(request, response);
        } catch (ApiException e) {
            handleException(response, e.getCode());
        } catch (ExpiredJwtException e) {
            log.warn("만료된 토큰[%s] 입니다.".formatted(accessToken));
            handleException(response, ExceptionCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            log.warn("유효하지 않은 토큰[%s] 입니다.".formatted(accessToken));
            handleException(response, ExceptionCode.ACCESS_DENIED);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURI = request.getRequestURI();
        return EXCLUDE_END_POINTS.stream()
                .anyMatch(pattern -> ANT_PATH_MATCHER.match(pattern, requestURI));
    }

    private boolean isValidToken(String authorizedHeader) {
        return StringUtils.hasText(authorizedHeader) && authorizedHeader.startsWith(GRANT_TYPE);
    }

    /**
     * 예외가 발생했을 때 HTTP Response에 상태 코드와 메시지 생성
     *
     * @param code 예외 코드
     * @throws IOException 입력 또는 출력 예외 발생
     */
    private void handleException(HttpServletResponse response, ExceptionCode code) throws IOException {
        response.setStatus(code.getHttpStatus().value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(code.getExternalMessage());
    }

}
