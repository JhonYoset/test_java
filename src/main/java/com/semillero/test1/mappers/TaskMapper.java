package com.semillero.test1.mappers;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.semillero.test1.dto.TaskRequestDto;
import com.semillero.test1.dto.TaskResponseDto;
import com.semillero.test1.models.StateEntity;
import com.semillero.test1.models.TaskEntity;
@Component
public class TaskMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
    public TaskEntity toEntity(TaskRequestDto dto){
        TaskEntity taskEntity = TaskEntity.builder()
            .taskTitle(dto.getTaskTitle())
            .taskDescription(dto.getTaskDescription())
            .userId(dto.getUserId())
            .build();
            
        // Manejar la relación con State - IMPORTANTE: usar setState()
        if (dto.getIdState() != null) {
            StateEntity state = StateEntity.builder()
                .idState(dto.getIdState())
                .build();
            taskEntity.setState(state);
        }
        
        return taskEntity;
    }

    public TaskResponseDto toDto(TaskEntity entity){
        return TaskResponseDto.builder()
            .idTask(entity.getIdTask())
            .taskTitle(entity.getTaskTitle())
            .taskDescription(entity.getTaskDescription())            
            .idState(entity.getState() != null ? entity.getState().getIdState() : null)
            .stateDescription(entity.getState()!=null ? entity.getState().getStateDescription():null)
            .userId(entity.getUserId())
            .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt().format(FORMATTER) : null)
            .updatedAt(entity.getUpdatedAt() != null ? entity.getUpdatedAt().format(FORMATTER) : null)
            .build();
    }
    
    public List<TaskResponseDto> toDtoList(List<TaskEntity> entities){
        return entities.stream()
            .map(this::toDto)
            .toList();
    }
}



