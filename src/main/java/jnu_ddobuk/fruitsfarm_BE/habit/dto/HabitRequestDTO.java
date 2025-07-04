package jnu_ddobuk.fruitsfarm_BE.habit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HabitRequestDTO {
    private Long memberId;
    private String type;
    private String achievement;
    private String motivation;
    private LocalDate startDate;
    private LocalDate endDate;
}
