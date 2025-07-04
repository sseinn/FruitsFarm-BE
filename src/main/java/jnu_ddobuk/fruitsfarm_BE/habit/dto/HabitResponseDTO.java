package jnu_ddobuk.fruitsfarm_BE.habit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HabitResponseDTO {

    @Builder.Default
    private int statusCode = 200;

    @Builder.Default
    private boolean success = true;

    private Object data;

    private String error;

    // 성공 응답 생성 메서드
    public static HabitResponseDTO success(Long habitTrackerId) {
        return HabitResponseDTO.builder()
                .statusCode(200)
                .success(true)
                .data(Map.of("habitTrackerId", habitTrackerId))
                .error(null)
                .build();
    }

    // 실패 응답 생성 메서드
    public static HabitResponseDTO error(int statusCode, String errorMessage) {
        return HabitResponseDTO.builder()
                .statusCode(statusCode)
                .success(false)
                .data(null)
                .error(errorMessage)
                .build();
    }
}