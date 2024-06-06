package ru.gb.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.model.Task;
import ru.gb.spring.service.FileGatwey;
import ru.gb.spring.service.TaskService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class Controller {

    private final TaskService taskService;
    private final FileGatwey fileGatwey;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){ //получение всех задач
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PostMapping("/add-task")
    public ResponseEntity<Task>addTask(@RequestParam String description){ // добавление задач
        return new ResponseEntity<>(taskService.addTask(description),HttpStatus.CREATED);

    }
    @PutMapping("update-task/{id}/{description}")  //обновление задач
    public ResponseEntity<Task>updateTask(@RequestParam Long id, @PathVariable String description){
        return new ResponseEntity<>(taskService.updateTask(id,description),HttpStatus.OK);

    }
    @GetMapping("/{id}")//получение задачи по id
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Task task;
        try {
            task = taskService.getTaskById(id);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Task());
        }
        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    @DeleteMapping("delete-task/{id}") //удаление задачи по id
    public ResponseEntity<Task> deleteTaskById (@PathVariable Long id ){
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }


}
