package com.semillero.test1.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateRequestDto {
    private String stateDescription;
    private String stateComment;
}
