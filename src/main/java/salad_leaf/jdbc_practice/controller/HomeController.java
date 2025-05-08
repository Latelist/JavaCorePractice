package salad_leaf.jdbc_practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    public HomeController() {}

    @GetMapping
    public String publicAccess(){
        return "Здарова, щегол";
    }
}
