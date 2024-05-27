package com.example;

import org.assertj.core.api.OptionalAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gb.spring.model.Task;
import ru.gb.spring.repository.TaskRepository;
import ru.gb.spring.service.TaskService;

import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class TaskServiceModelTest {
    @InjectMocks
    private TaskService service;
    @Mock
    private TaskRepository repository;

    private static Task taskForTest;
    @Test
    public void addTaskCorrectFlow(){
        //блок предусловия
      String description = "Description for Test";

      Task saveTask = service.addTask(description);

        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
       verify(repository).save(taskArgumentCaptor.capture());

       Task capteredTask = taskArgumentCaptor.getValue();
       assertThat (capteredTask.getDescription()).isEqualTo(description);

    }
    @Test
    public void updateTaskExceptionFlow(){
        //блок предусловия
        Long id = 1L;
        String description = "Description for Test";
       BDDMockito.given(repository.findById(id)).willReturn(Optional.empty());


        assertThrows(NullPointerException.class,()-> service.updateTask(id,description));


    }




}
