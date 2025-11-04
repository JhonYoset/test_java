package com.semillero.test1.service;

import java.util.List;

import com.semillero.test1.commons.iCrudCommonsDto;
import com.semillero.test1.dto.StateRequestDto;
import com.semillero.test1.dto.StateResponseDto;

public interface IStateService extends iCrudCommonsDto <StateRequestDto, StateResponseDto, Long>{
    List <StateResponseDto> findAll();
}
