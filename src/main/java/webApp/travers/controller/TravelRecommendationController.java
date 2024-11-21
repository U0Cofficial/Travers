
package webApp.travers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webApp.travers.service.TravelRecommendationService;
import webApp.travers.domain.TravelRecommendation;

import java.util.List;

@Controller
@RequestMapping("/recommendation")
public class TravelRecommendationController {

    private final TravelRecommendationService recommendationService;

    @Autowired
    public TravelRecommendationController(TravelRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public String showRecommendationForm() {
        return "recommendation/recommendationTravel"; // 여행 추천 폼 페이지
    }

    @PostMapping
    public String getRecommendations(@RequestParam String companionType,
                                     @RequestParam String moodType,
                                     @RequestParam String seasonType,
                                     Model model) {
        List<TravelRecommendation> recommendations = recommendationService.getRecommendations(
                companionType, moodType, seasonType);
        model.addAttribute("recommendations", recommendations);
        return "recommendation/travelRecommendations"; // 추천 결과 페이지
    }
}
