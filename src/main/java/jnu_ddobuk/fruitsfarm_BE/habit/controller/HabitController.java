package jnu_ddobuk.fruitsfarm_BE.habit.controller;

import jnu_ddobuk.fruitsfarm_BE.habit.dto.HabitRequestDTO;
import jnu_ddobuk.fruitsfarm_BE.habit.dto.HabitResponseDTO;
import jnu_ddobuk.fruitsfarm_BE.habit.service.HabitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/habit-trackers")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public ResponseEntity<HabitResponseDTO> createHabit(@RequestBody HabitRequestDTO requestDTO) {
        try {
            log.info("습관 등록 요청: {}", requestDTO);

            Long habitId = habitService.createHabit(requestDTO);
            HabitResponseDTO responseDTO = HabitResponseDTO.success(habitId);

            log.info("습관 등록 성공: habitId = {}", habitId);
            return ResponseEntity.ok(responseDTO);

        } catch (IllegalArgumentException e) {
            log.error("습관 등록 실패 - 잘못된 요청: {}", e.getMessage());
            HabitResponseDTO errorResponse = HabitResponseDTO.error(400, e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);

        } catch (Exception e) {
            log.error("습관 등록 실패 - 서버 오류: {}", e.getMessage());
            HabitResponseDTO errorResponse = HabitResponseDTO.error(500, "서버 내부 오류가 발생했습니다.");
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
}