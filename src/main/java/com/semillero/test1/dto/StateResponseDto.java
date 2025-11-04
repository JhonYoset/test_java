package com.semillero.test1.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateResponseDto {
    private Long idState;
    private String stateDescription;
    private String stateComment;
    private String updatedAt;
    private String createdAt;
}
