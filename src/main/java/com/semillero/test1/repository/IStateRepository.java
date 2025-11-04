package com.semillero.test1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semillero.test1.models.StateEntity;

public interface IStateRepository extends JpaRepository<StateEntity, Long> {
    
}
