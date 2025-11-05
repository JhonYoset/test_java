package com.semillero.test1.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_task")
    private Long idTask;
    @Column(name="task_title")
    private String taskTitle;
    @Column(name="task_description")
    private String taskDescription;
    
    
    @ManyToOne
    @JoinColumn(name = "id_state", referencedColumnName = "id_state")
    private StateEntity state;

    
    @Column(name="user_id")
    private Long userId;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
   
    @PrePersist
    protected void OnCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt= LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdated(){
        this.updatedAt= LocalDateTime.now();
    }
}