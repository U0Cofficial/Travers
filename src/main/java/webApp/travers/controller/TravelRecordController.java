
package webApp.travers.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webApp.travers.domain.TravelRecord;
import webApp.travers.domain.User;
import webApp.travers.service.TravelRecordService;
import org.springframework.web.multipart.MultipartFile;

//import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/record")
public class TravelRecordController {

    private final TravelRecordService travelRecordService;

    @Autowired
    public TravelRecordController(TravelRecordService travelRecordService) {
        this.travelRecordService = travelRecordService;
    }

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @GetMapping
    public String showTravelRecords(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("travelRecords", travelRecordService.getTravelRecordsByUser(user.getId()));
        return "record/travelRecords"; // 여행 기록 목록 페이지
    }

    @GetMapping("/create")
    public String showCreateForm() {
        return "record/createTravelRecord"; // 여행 기록 생성 폼
    }
    @PostMapping("/create")
    public String createTravelRecord(@RequestParam String title,
                                     @RequestParam String description,
                                     @RequestParam("image") MultipartFile file,
                                     HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/user/login";
        }

        String imageUrl = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
                Files.write(path, bytes);
//                imageUrl = path.toString();
                imageUrl = "/uploads/" + file.getOriginalFilename();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        TravelRecord travelRecord = TravelRecord.builder()
                .user(user)
                .title(title)
                .description(description)
                .imageUrl(imageUrl)
                .createdAt(LocalDateTime.now())
                .build();

        travelRecordService.createTravelRecord(travelRecord);
        return "redirect:/record";
    }

    @GetMapping("/details/{id}")
    public String viewTravelRecordDetails(@PathVariable Long id, Model model) {
        Optional<TravelRecord> travelRecord = travelRecordService.getTravelRecordById(id);
        if (travelRecord.isPresent()) {
            model.addAttribute("travelRecord", travelRecord.get());
            return "record/travelRecordDetails"; // 여행 기록 상세 페이지
        }
        return "redirect:/record";
    }

    @PostMapping("/delete/{id}")
    public String deleteTravelRecord(@PathVariable Long id) {
        travelRecordService.deleteTravelRecord(id);
        return "redirect:/record";
    }
}

