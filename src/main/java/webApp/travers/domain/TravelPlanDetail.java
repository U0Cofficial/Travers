package webApp.travers.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelPlanDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "travel_plan_id", nullable = false)
    private TravelPlan travelPlan;

    private LocalDate date;
    private String timeSlot;
    private String activity;
}
