package jnu_ddobuk.fruitsfarm_BE.habit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jnu_ddobuk.fruitsfarm_BE.habit.dto.HabitRequestDTO;
import jnu_ddobuk.fruitsfarm_BE.habit.entity.Habit;
import jnu_ddobuk.fruitsfarm_BE.habit.repository.HabitRepository;
import jnu_ddobuk.fruitsfarm_BE.member.entity.Member;
import jnu_ddobuk.fruitsfarm_BE.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HabitService {

    private final HabitRepository habitRepository;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 습관 등록 처리
     */
    @Transactional
    public Long createHabit(HabitRequestDTO request) {
        log.info("[HabitService] 습관 등록 요청 - memberId: {}, type: {}", request.getMemberId(), request.getType());

        // 1. 회원 확인 (Optional 대신 null 체크)
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. ID = " + request.getMemberId()));


        // 2. 타입에 따라 progress 크기 결정
        int progressSize = switch (request.getType().toLowerCase()) {
            case "grape" -> 10;
            case "watermelon" -> 30;
            default -> throw new IllegalArgumentException("지원하지 않는 타입입니다: " + request.getType());
        };

        // 3. false로 채워진 progress 리스트 생성
        List<Boolean> progressList = new ArrayList<>();
        for (int i = 0; i < progressSize; i++) {
            progressList.add(false);
        }

        // 4. progress 리스트를 JSON 문자열로 변환
        String progressJson;
        try {
            progressJson = objectMapper.writeValueAsString(progressList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("progress 변환 실패", e);
        }

        // 5. Habit 객체 생성
        Habit habit = new Habit(
                member,
                request.getType(),
                request.getAchievement(),
                request.getMotivation(),
                request.getStartDate(),
                request.getEndDate(),
                progressJson
        );

        // 6. 저장 및 반환
        habitRepository.save(habit);
        log.info("[HabitService] 습관 등록 완료 - habitId: {}", habit.getId());
        return habit.getId();
    }
}
