package vsu.ru.todolist.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.ru.todolist.dto.TaskDto;
import vsu.ru.todolist.entity.TaskEntity;
import vsu.ru.todolist.repository.TaskRepository;
import vsu.ru.todolist.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(TaskDto task) {
        TaskEntity taskEntity = toEntity(task);
        taskRepository.save(taskEntity);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElse(null);
        return toDto(taskEntity);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<TaskEntity> taskEntities = taskRepository.findAll();
        return taskEntities.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public void updateTask(TaskDto task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("Task ID must be provided");
        }
        TaskEntity taskEntity = toEntity(task);
        taskRepository.save(taskEntity);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskEntity toEntity(TaskDto dto) {
        if (dto == null) {
            return null;
        }
        return new TaskEntity(
            dto.getId(),
            dto.getTitle(),
            dto.getDescription(),
            dto.isDone(),
            dto.getAuthor()
        );
    }

    private TaskDto toDto(TaskEntity entity) {
        if (entity == null) {
            return null;
        }
        return new TaskDto(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.isDone(),
            entity.getAuthor()
        );
    }
}
