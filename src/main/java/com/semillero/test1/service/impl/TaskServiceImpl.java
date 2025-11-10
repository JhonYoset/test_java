package com.semillero.test1.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.semillero.test1.dto.TaskRequestDto;
import com.semillero.test1.dto.TaskResponseDto;
import com.semillero.test1.exception.ResourceNotFoundException;
import com.semillero.test1.exception.RuntimeCustomException;
import com.semillero.test1.mappers.TaskMapper;
import com.semillero.test1.models.StateEntity;
import com.semillero.test1.models.TaskEntity;
import com.semillero.test1.service.ITaskService;
import com.semillero.test1.repository.IStateRepository;
import com.semillero.test1.repository.ITaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService{
    
    private final ITaskRepository iTaskRepository;    
    private final IStateRepository iStateRepository;
    private final TaskMapper taskMapper;
    
    @Override
    public TaskResponseDto save(TaskRequestDto taskRequestDto){
        TaskEntity taskEntity = taskMapper.toEntity(taskRequestDto);

        Long idState = taskRequestDto.getIdState();

        Optional<StateEntity> stateEntity = iStateRepository.findById(idState);
        if(!stateEntity.isPresent()){
            throw new ResourceNotFoundException("No existe el IdEstado");
        }
        taskEntity.setState(stateEntity.get());

        try{
            TaskEntity savedEntity = iTaskRepository.save(taskEntity);
            return taskMapper.toDto(savedEntity);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"ERROR AL GUARDAR LA TAREA");
        }
    }
    
    @Override
    public TaskResponseDto update(Long id, TaskRequestDto taskRequestDto){
        TaskEntity existing = iTaskRepository.findById(id)
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
        
        TaskEntity updatedEntity = iTaskRepository.save(existing);
        return taskMapper.toDto(updatedEntity);
    }
    
    @Override
    public TaskResponseDto findById(Long id) {
        TaskEntity existing = iTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        return taskMapper.toDto(existing);
    }
    
    @Override
    public TaskResponseDto delete(Long id) {
        TaskEntity existing = iTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        iTaskRepository.delete(existing);
        return taskMapper.toDto(existing);
    }

    @Override
    public List<TaskResponseDto> findAll(){
        List<TaskEntity> entities = iTaskRepository.findAll();
        return taskMapper.toDtoList(entities);
    }
}


