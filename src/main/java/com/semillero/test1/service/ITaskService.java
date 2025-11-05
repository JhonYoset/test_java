package com.semillero.test1.service;

import java.util.List;

import com.semillero.test1.commons.iCrudCommonsDto;
import com.semillero.test1.dto.TaskRequestDto;
import com.semillero.test1.dto.TaskResponseDto;

public interface ITaskService extends iCrudCommonsDto <TaskRequestDto, TaskResponseDto, Long> {
    List<TaskResponseDto> findAll();
}
