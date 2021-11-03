package great.dog.api.controller.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class LoginController {

    @GetMapping(value = "")
    public String login(@AuthenticationPrincipal User user) {
        if(!Objects.isNull(user)) {
            if(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_VIEW"))) {
                return "redirect:/v1";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/v1")
    public String main() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/err/denied-page")
    public String test() {
        return "err";
    }


}
