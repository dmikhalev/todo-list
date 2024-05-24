package vsu.ru.todolist.service;

import java.util.List;
import vsu.ru.todolist.dto.TaskDto;

public interface TaskService {

    void createTask(TaskDto task);

    TaskDto getTaskById(Long id);

    List<TaskDto> getAllTasks();

    void updateTask(TaskDto task);

    void deleteTaskById(Long id);
}
