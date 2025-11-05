package com.semillero.test1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.semillero.test1.service.ITaskService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final ITaskService taskService;


    @PostMapping
    public ResponseEntity<TaskResponseDto> create(@RequestBody TaskRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> update(@PathVariable Long id, @RequestBody TaskRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(id, dto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> get(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findById(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.delete(id));
    }
    
    // Endpoint adicional para obtener todas las tareas
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }
}

