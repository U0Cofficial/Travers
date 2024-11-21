
package webApp.travers.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webApp.travers.domain.User;
import webApp.travers.service.UserService;
//import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            userService.registerUser(username, password);
            return "redirect:/user/login";
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "user/register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        System.out.println("Attempting login for user: " + username);

        return userService.authenticate(username, password)
                .map(user -> {
                    System.out.println("User authenticated successfully. Adding user to session.");
                    session.setAttribute("loggedInUser", user);

                    // Debugging the session state
                    System.out.println("Session ID: " + session.getId());
                    System.out.println("Session creation time: " + new java.util.Date(session.getCreationTime()));
                    System.out.println("Session last accessed time: " + new java.util.Date(session.getLastAccessedTime()));
                    System.out.println("Logged in user stored in session: " + session.getAttribute("loggedInUser"));

                    return "redirect:/user/dashboard";
                })
                .orElseGet(() -> {
                    System.out.println("Authentication failed for user: " + username);
                    model.addAttribute("error", "Invalid username or password");
                    return "user/login";
                });
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("username", user.getUsername());
        return "user/dashboard"; // 기능 선택 페이지
    }
}
