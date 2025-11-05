package com.semillero.test1.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.semillero.test1.dto.TaskRequestDto;
import com.semillero.test1.dto.TaskResponseDto;
import com.semillero.test1.mappers.TaskMapper;
import com.semillero.test1.models.StateEntity;
import com.semillero.test1.models.TaskEntity;
import com.semillero.test1.service.ITaskService;
import com.semillero.test1.repository.ITaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService{
    
    private final ITaskRepository taskRepository;
    private final TaskMapper taskMapper;
    
    @Override
    public TaskResponseDto save(TaskRequestDto taskRequestDto){
        TaskEntity taskEntity = taskMapper.toEntity(taskRequestDto);
        TaskEntity savedEntity = taskRepository.save(taskEntity);
        return taskMapper.toDto(savedEntity);
    }
    
    @Override
    public TaskResponseDto update(Long id, TaskRequestDto taskRequestDto){
        TaskEntity existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        
        // Actualizar campos
        existing.setTaskTitle(taskRequestDto.getTaskTitle());
        existing.setTaskDescription(taskRequestDto.getTaskDescription());
        existing.setUserId(taskRequestDto.getUserId());
        
        // Actualizar state si se proporciona
        if (taskRequestDto.getIdState() != null) {
            existing.setState(StateEntity.builder()
                .idState(taskRequestDto.getIdState())
                .build());
        }
        
        TaskEntity updatedEntity = taskRepository.save(existing);
        return taskMapper.toDto(updatedEntity);
    }
    
    @Override
    public TaskResponseDto findById(Long id) {
        TaskEntity existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        return taskMapper.toDto(existing);
    }
    
    @Override
    public TaskResponseDto delete(Long id) {
        TaskEntity existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        taskRepository.delete(existing);
        return taskMapper.toDto(existing);
    }

    @Override
    public List<TaskResponseDto> findAll(){
        List<TaskEntity> entities = taskRepository.findAll();
        return taskMapper.toDtoList(entities);
    }
}


