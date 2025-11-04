package com.semillero.test1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semillero.test1.dto.StateRequestDto;
import com.semillero.test1.dto.StateResponseDto;
import com.semillero.test1.service.IStateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/states")

public class StateController {
    private final IStateService stateService;//inyectar dependencias
    @PostMapping
    public ResponseEntity<StateResponseDto> create(@RequestBody StateRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(stateService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity <StateResponseDto> update (@PathVariable Long id, @RequestBody StateRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(stateService.update(id,dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity <StateResponseDto> get(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(stateService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <StateResponseDto> delete (@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(stateService.delete(id));
    }
    
}
