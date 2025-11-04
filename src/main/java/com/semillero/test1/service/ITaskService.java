package com.semillero.test1.service;

import java.util.List;

import com.semillero.test1.commons.iCrudCommons;
import com.semillero.test1.models.TaskEntity;

public interface ITaskService extends iCrudCommons <TaskEntity, Long> {
    List<TaskEntity> findAll();
}
