package jnu_ddobuk.fruitsfarm_BE.habit.entity;

import jakarta.persistence.*;
import jnu_ddobuk.fruitsfarm_BE.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK: 습관 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 어떤 회원이 만든 습관인지 (FK)

    @Column(nullable = false)
    private String type; // "grape" or "watermelon"

    @Column(nullable = false)
    private String achievement; // 습관 목표 내용

    @Column(nullable = false)
    private String motivation; // 습관에 대한 동기

    @Column(nullable = false)
    private LocalDate startDate; // 시작 날짜

    @Column(nullable = false)
    private LocalDate endDate; // 끝 날짜

    @Column(columnDefinition = "TEXT", nullable = false)
    private String progress; // JSON 형태 문자열: "[false, false, false, ...]"

    // 생성자 (필드 초기화용, 필요에 따라 커스터마이징 가능)
    public Habit(Member member, String type, String achievement, String motivation,
                 LocalDate startDate, LocalDate endDate, String progress) {
        this.member = member;
        this.type = type;
        this.achievement = achievement;
        this.motivation = motivation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
    }
}
