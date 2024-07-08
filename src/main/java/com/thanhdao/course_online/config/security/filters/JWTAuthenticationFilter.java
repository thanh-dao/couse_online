package com.thanhdao.course_online.config.security.filters;

import com.thanhdao.course_online.entity.User;
import com.thanhdao.course_online.utils.constants.RequestConstants;
import com.thanhdao.course_online.utils.enums.TokenType;
import com.thanhdao.course_online.service.JwtService;
import com.thanhdao.course_online.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;

//@Component
@Slf4j
@RequiredArgsConstructor
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {


            // Lấy jwt từ request
            String jwt = getJwtFromRequest(request);
            System.out.println(jwt);
            if (StringUtils.hasText(jwt))  {
                Map<String, Object> claims = jwtService.getClaimsFromToken(TokenType.ACCESSTOKEN, jwt);
                String userEmail = claims.get("sub").toString();
                UserDetails userDetails = userService.loadUserByUsername(userEmail);

//                System.out.println(userDetails);
                if(userDetails != null) {
                    UsernamePasswordAuthenticationToken
                            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    request.setAttribute(RequestConstants.currentUserAttributeName, ((User)userDetails));
//                    var rq = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getAttribute("userId");
//                    log.info(rq.toString());
                }
            }
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
            response.setStatus(FORBIDDEN.value());
            response.setHeader("Exception", "Expired JWT token");
//            ExceptionResponse exceptionResponse = new ExceptionResponse(
//                    "Expired JWT token",
//                    "Your JWT Token is expired"
//            );
//            response.setContentType("application/json");
//            response.getWriter().println(new ObjectMapper().writeValueAsString(exceptionResponse));
        }
    }
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
//        System.out.println(bearerToken);
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}