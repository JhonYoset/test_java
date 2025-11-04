package com.semillero.test1.mappers;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.semillero.test1.dto.TaskRequestDto;
import com.semillero.test1.dto.TaskResponseDto;
import com.semillero.test1.models.TaskEntity;
@Component
public class TaskMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public TaskEntity toEntity(TaskRequestDto dto){
        return TaskEntity.builder()
        .taskTitle(dto.getTaskTitle())
        .taskDescription(dto.getTaskDescription())
        .userId(dto.getUserId())
        .build();
    }

    public TaskResponseDto toDto(TaskEntity entity){
        return TaskResponseDto.builder()
        .idTask(entity.getIdTask())
        .taskTitle(entity.getTaskTitle())
        .taskDescription(entity.getTaskDescription())
        .userId(entity.getUserId())
        .createdAt(entity.getCreatedAt() !=null ? entity.getCreatedAt().format(FORMATTER):null)
        .updatedAt(entity.getUpdatedAt() !=null ? entity.getUpdatedAt().format(FORMATTER):null)
        .build();
    }

}
