package com.vlzov.task.service;

import com.vlzov.task.dto.TaskRequest;
import com.vlzov.task.dto.TaskResponse;
import com.vlzov.task.entity.Task;
import com.vlzov.task.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing task service layer with JUnit technology")
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    @DisplayName("create: should save request and return response")
    void create_WithValidRequest_ReturnsTaskResponse() {

        // ---------------------- Подготовка ----------------------

        // Входные данные
        TaskRequest request = new TaskRequest("title", "description");

        // Выходные данные
        Task saved = Task.builder()
                .id(1L)
                .title("title")
                .description("description")
                .completed(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(taskRepository.save(any(Task.class))).thenReturn(saved);


        // ---------------------- Бизнес-логика ----------------------

        TaskResponse response = taskService.create(request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getTitle()).isEqualTo("title");
        assertThat(response.getDescription()).isEqualTo("description");
        assertThat(response.isCompleted()).isFalse();

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).save(taskCaptor.capture());
        Task captureTask = taskCaptor.getValue();

        assertThat(captureTask.getTitle()).isEqualTo("title");
        assertThat(captureTask.getDescription()).isEqualTo("description");

    }

}