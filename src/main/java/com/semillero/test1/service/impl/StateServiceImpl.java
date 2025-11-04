package com.semillero.test1.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.semillero.test1.dto.StateRequestDto;
import com.semillero.test1.dto.StateResponseDto;
import com.semillero.test1.mappers.StateMapper;
import com.semillero.test1.models.StateEntity;
import com.semillero.test1.repository.IStateRepository;
import com.semillero.test1.service.IStateService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class StateServiceImpl implements IStateService{
    
    private final IStateRepository iStateRepository;
    private final StateMapper stateMapper;
    
    @Override
    public StateResponseDto save(StateRequestDto stateRequestDto){
        StateEntity stateEntity = stateMapper.toEntity(stateRequestDto);
        StateEntity savedEntity = iStateRepository.save(stateEntity);

        return stateMapper.toDto(savedEntity);
    }
    @Override
    public StateResponseDto update(Long id, StateRequestDto stateRequestDto){
        StateEntity existing = iStateRepository.findById(id).orElseThrow(()-> new RuntimeException("state not created"));
        StateEntity stateEntity = stateMapper.toEntity(stateRequestDto);

        BeanUtils.copyProperties(stateEntity, existing, "idState", "createdAt");
        existing.setUpdatedAt(LocalDateTime.now());
        StateEntity updatedEntity = iStateRepository.save(existing);
        return stateMapper.toDto(updatedEntity);
    }
    @Override
    public StateResponseDto findById(Long id) {
        StateEntity existing = iStateRepository.findById(id).orElseThrow(()-> new RuntimeException("State not found"));
        return stateMapper.toDto(existing);
    }
    @Override
    public StateResponseDto  delete(Long id) {
        StateEntity existing = iStateRepository.findById(id).orElseThrow(()->new RuntimeException("State not found"));
        iStateRepository.delete(existing);
        return stateMapper.toDto(existing);
    }

    @Override
    public List<StateResponseDto> findAll(){
        List<StateEntity> entities= iStateRepository.findAll();
        return stateMapper.toDtoList(entities);
    }
}
