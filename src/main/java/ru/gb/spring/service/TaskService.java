package ru.gb.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.aspect.UserAction;
import ru.gb.spring.model.Task;
import ru.gb.spring.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() { // получение списка всех задач
        return taskRepository.findAll();
    }
    @UserAction
    public Task addTask(String description) {  //добавление новой задачи
        Task task = new Task();
        task.setId(task.getId());
        task.setDescription(description);
        task.setLocalDateTime(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id){  // поиск по id
        return taskRepository.findById(id).orElseThrow(null);
    }
    public Task updateTask( Long id,String description){ //обновление  задачи
       Task task =  taskRepository.findById(id).orElse(null);
        assert task != null;
        taskRepository.save(task);
        return task;

    }
    public void deleteTask(Long id) {   //удаление задачи по id
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        taskRepository.delete(task);
    }


}