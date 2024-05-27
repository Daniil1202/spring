package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.spring.model.Task;
import ru.gb.spring.repository.TaskRepository;
import ru.gb.spring.service.TaskService;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@SpringBootTest

public class TaskServiceIntegrationTest {
    @MockBean
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;
    @Test
    void deleteTaskCorrectFlow(){
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        given(taskRepository.findById(id)).willReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(task);

        taskService.deleteTask(id);
        // блок проверки
        verify(taskRepository).delete(task);
    }

}
