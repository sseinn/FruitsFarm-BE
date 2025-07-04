package jnu_ddobuk.fruitsfarm_BE.habit.repository;

import jnu_ddobuk.fruitsfarm_BE.habit.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HabitRepository extends JpaRepository<Habit, Long> {
}
