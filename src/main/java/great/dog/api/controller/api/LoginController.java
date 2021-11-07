package great.dog.api.controller.api;

import great.dog.api.config.SecurityUser;
import great.dog.api.domain.dto.UserDto;
import great.dog.api.domain.entity.UserRole;
import great.dog.api.domain.response.DefaultRes;
import great.dog.api.service.UserService;
import great.dog.api.util.StatusCode;
import great.dog.api.util.StatusMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping(value = "")
    public String index(@AuthenticationPrincipal SecurityUser securityUser) {
        if(securityUser != null) {
            if(securityUser.getRoleTypes().contains(UserRole.RoleType.ROLE_VIEW)) {
                return "redirect:/swagger-ui.html";
            }
        }
        return "login/login";
    }

    @GetMapping(value = "/login")
    public String login(@AuthenticationPrincipal SecurityUser securityUser) {
        if(securityUser != null) {
            if(securityUser.getRoleTypes().contains(UserRole.RoleType.ROLE_VIEW)) {
                return "redirect:/v1";
            }
        }
        return "login/login";
    }

    @PostMapping(value = "/login")
    public String login2(@AuthenticationPrincipal SecurityUser securityUser) {
        if(securityUser != null) {
            if(securityUser.getRoleTypes().contains(UserRole.RoleType.ROLE_VIEW)) {
                return "redirect:/v1";
            }
        }
        return "login/login";
    }

    @GetMapping("/v1")
    public String main() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping(value = "/join")
    public String joinForm(@AuthenticationPrincipal SecurityUser securityUser) {
        if(securityUser != null && securityUser.getRoleTypes().contains(UserRole.RoleType.ROLE_VIEW)) {
            return "redirect:/v1";
        }
        return "login/join";
    }

    @GetMapping("/err/denied-page")
    public String test() {
        return "err";
    }

    @GetMapping("/encode/{password}")
    public String restorePassword(@PathVariable("password") String password) {
        return passwordEncoder.encode(password);
    }


    @ResponseBody
    @PostMapping(value = "/join")
    public ResponseEntity<DefaultRes<?>> join(@RequestBody UserDto.SaveRequest dto){
        DefaultRes<?> defaultRes = new DefaultRes<Object>(dto);
        if(userService.existsByEmail(dto.getEmail()) == true) {
            defaultRes.setResMsg(StatusMsg.CREATED_FAIL);
        } else {
            int result = userService.save(dto);
            if (result > 0) {
                defaultRes.setResCode(StatusCode.OK);
                defaultRes.setResMsg(StatusMsg.CREATED_SUCCESS);
            }
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }



}
