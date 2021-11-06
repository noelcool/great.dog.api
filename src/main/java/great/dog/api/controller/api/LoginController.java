package great.dog.api.controller.api;

import great.dog.api.config.SecurityUser;
import great.dog.api.domain.entity.UserRole;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;

@Controller
public class LoginController {

    private final PasswordEncoder passwordEncoder;

    public LoginController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping(value = "/")
//    public String index(@AuthenticationPrincipal User user){
//        if(user != null) {
//            if(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_VIEW"))) {
//                return "redirect:/v1";
//            }
//        }
//        return "redirect:/login";
//    }

    @GetMapping(value = "/login")
    public String index(@AuthenticationPrincipal SecurityUser securityUser) {
        if(securityUser != null) {
            if(securityUser.getRoleTypes().contains(UserRole.RoleType.ROLE_VIEW)) {
                return "redirect:/v1";
            }
        }
        return "login/login";
    }

    @GetMapping("/v1")
    public String main() {
        //return "redirect:/swagger-ui.html";
        return "content/main";
    }

    @GetMapping("/err/denied-page")
    public String test() {
        return "err";
    }

    @GetMapping("/encode/{password}")
    public String restorePassword(@PathVariable("password") String password) {
        return passwordEncoder.encode(password);
    }


}
