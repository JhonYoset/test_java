package com.semillero.test1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semillero.test1.models.TaskEntity;

public interface ITaskRepository extends JpaRepository <TaskEntity,Long>{
    
}
