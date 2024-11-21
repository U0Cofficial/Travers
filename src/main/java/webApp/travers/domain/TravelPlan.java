package webApp.travers.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String description;

    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelPlanDetail> travelPlanDetails;
}
