package ru.gb.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.spring.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
