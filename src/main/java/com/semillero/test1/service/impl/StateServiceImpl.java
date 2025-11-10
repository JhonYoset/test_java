package com.semillero.test1.service.impl;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.semillero.test1.dto.StateRequestDto;
import com.semillero.test1.dto.StateResponseDto;
import com.semillero.test1.exception.ResourceNotFoundException;
import com.semillero.test1.mappers.StateMapper;
import com.semillero.test1.models.StateEntity;
import com.semillero.test1.repository.IStateRepository;
import com.semillero.test1.service.IStateService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class StateServiceImpl implements IStateService {

    private final IStateRepository istateRepository;
    private final StateMapper stateMapper;

    @Override
    public com.semillero.test1.dto.StateResponseDto save(StateRequestDto dto) {
        StateEntity entity = stateMapper.toEntity(dto);
        StateEntity savedEntity = istateRepository.save(entity);
        return stateMapper.toDto(savedEntity);
    }

    @Override
    public StateResponseDto update(Long id, StateRequestDto stateRequestDto) {
        StateEntity existing = istateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("State not found with id: " + id)); 
        StateEntity stateEntity = stateMapper.toEntity(stateRequestDto);
        BeanUtils.copyProperties(stateEntity, existing, "idState", "CreatedAt");
        existing.setUpdatedAt(java.time.LocalDateTime.now());
        StateEntity updatedEntity = istateRepository.save(existing);
        return stateMapper.toDto(updatedEntity);
    }

    @Override
    public StateResponseDto findById(Long id) {
        StateEntity entity = istateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("State not found with id: " + id));
        return stateMapper.toDto(entity);
    }

    @Override
    public StateResponseDto delete(Long id) {
        StateEntity entity = istateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("State not found with id: " + id));
        istateRepository.delete(entity);
        return stateMapper.toDto(entity);
    }

    @Override
    public List<StateResponseDto> findAll() {
        List<StateEntity> entities = istateRepository.findAll();
        return stateMapper.toDtoList(entities);
    }
}