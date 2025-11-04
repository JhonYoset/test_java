package com.semillero.test1.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.semillero.test1.models.TaskEntity;
import com.semillero.test1.service.ITaskService;
import com.semillero.test1.repository.ITaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService{
    
    private final ITaskRepository ITaskRepository;
    
    @Override
    public TaskEntity save(TaskEntity entity){
        return ITaskRepository.save(entity);
    }
    @Override
    public TaskEntity update(Long id, TaskEntity entity){
        TaskEntity existing = ITaskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
        
        BeanUtils.copyProperties(entity, existing, "idTask", "createdAt");
        existing.setUpdatedAt(LocalDateTime.now());
        return ITaskRepository.save(existing);
    }
    @Override
    public TaskEntity findById(Long id) {
        return ITaskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
    }
    @Override
    public TaskEntity delete(Long id) {
        TaskEntity existing = ITaskRepository.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
        ITaskRepository.delete(existing);
        return existing;
    }

    @Override
    public List<TaskEntity> findAll(){
        return ITaskRepository.findAll();
    }
}
