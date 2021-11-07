package great.dog.api.config;

import great.dog.api.domain.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        if(e instanceof AccessDeniedException) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication();
                Set<UserRole.RoleType> roleTypes = securityUser.getRoleTypes();
                if (!roleTypes.isEmpty()) {
                    request.setAttribute("msg", "접근 권한이 없습니다");
                    if(roleTypes.contains(UserRole.RoleType.ROLE_VIEW)) {
                        request.setAttribute("nextPage", "/v1");
                    }
                }
            }
        } else {
            logger.info(e.getClass().getCanonicalName());
        }
        request.getRequestDispatcher("/err/denied-page").forward(request, response);
    }
}
