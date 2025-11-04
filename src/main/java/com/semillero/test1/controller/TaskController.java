package com.semillero.test1.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semillero.test1.dto.TaskRequestDto;
import com.semillero.test1.dto.TaskResponseDto;
import com.semillero.test1.mappers.TaskMapper;
import com.semillero.test1.models.TaskEntity;
import com.semillero.test1.service.ITaskService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")

public class TaskController {
    private final ITaskService taskService;//inyectar dependencias
    private final TaskMapper taskMapper;

    @PostMapping
    public TaskResponseDto create(@RequestBody TaskRequestDto dto){
        TaskEntity entity = taskMapper.toEntity(dto);
        return taskMapper.toDto(taskService.save(entity));
    }

    @PutMapping("/{id}")
    public TaskResponseDto update (@PathVariable Long id, @RequestBody TaskRequestDto dto){
        TaskEntity entity = taskMapper.toEntity(dto);
        return taskMapper.toDto(taskService.update(id,entity));
    }
    @GetMapping("/{id}")
    public TaskResponseDto get(@PathVariable Long id){
        return taskMapper.toDto(taskService.findById(id));
    }
    @DeleteMapping("/{id}")
    public TaskResponseDto delete (@PathVariable Long id){
        return taskMapper.toDto(taskService.delete(id));
    }
    
}
