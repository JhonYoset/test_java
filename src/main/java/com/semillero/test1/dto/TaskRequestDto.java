package com.semillero.test1.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskRequestDto {
    private String taskTitle;
    private String taskDescription;
    private Long idState;
    private Long userId;
}
