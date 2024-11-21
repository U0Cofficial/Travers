
package webApp.travers.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webApp.travers.domain.TravelPlan;
import webApp.travers.domain.User;
import webApp.travers.service.TravelPlanService;

//import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/plan")
public class TravelPlanController {

    private final TravelPlanService travelPlanService;

    @Autowired
    public TravelPlanController(TravelPlanService travelPlanService) {
        this.travelPlanService = travelPlanService;
    }

    @GetMapping
    public String showTravelPlans(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("travelPlans", travelPlanService.getTravelPlansByUser(user.getId()));
        return "plan/travelPlans"; // 여행 계획 목록 페이지
    }

    @GetMapping("/create")
    public String showCreateForm() {
        return "plan/createTravelPlans"; // 여행 계획 생성 폼
    }

    @PostMapping("/create")
    public String createTravelPlan(@RequestParam String title,
                                   @RequestParam String description,
                                   @RequestParam String startDate,
                                   @RequestParam String endDate,
                                   HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/user/login";
        }

        TravelPlan travelPlan = TravelPlan.builder()
                .user(user)
                .title(title)
                .description(description)
                .startDate(LocalDate.parse(startDate))
                .endDate(LocalDate.parse(endDate))
                .build();

        travelPlanService.createTravelPlan(travelPlan);
        return "redirect:/plan";
    }

    @GetMapping("/details/{id}")
    public String viewTravelPlanDetails(@PathVariable Long id, Model model) {
        Optional<TravelPlan> travelPlan = travelPlanService.getTravelPlanById(id);
        if (travelPlan.isPresent()) {
            model.addAttribute("travelPlan", travelPlan.get());
            return "plan/travelPlanDetails"; // 여행 계획 상세 페이지
        }
        return "redirect:/plan";
    }

    @PostMapping("/delete/{id}")
    public String deleteTravelPlan(@PathVariable Long id) {
        travelPlanService.deleteTravelPlan(id);
        return "redirect:/plan";
    }
}
