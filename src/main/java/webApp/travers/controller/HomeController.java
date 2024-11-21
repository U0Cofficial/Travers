package webApp.travers.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index"; // `src/main/resources/templates/index.html` 페이지로 매핑
    }
}
