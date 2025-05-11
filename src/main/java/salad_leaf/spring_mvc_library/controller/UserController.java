package salad_leaf.spring_mvc_library.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/logged-out")
    public String notAuthenticated(){
        return "Юзер, залогинься.";
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        String login = principal.getAttribute("login");
        String email = principal.getAttribute("email");

        return Map.of(
                "login", login != null ? login : "unknown",
                "email", email != null ? email : "not provided"
        );
    }
}
