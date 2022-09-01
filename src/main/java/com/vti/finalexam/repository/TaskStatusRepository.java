package com.vti.finalexam.repository;

import com.vti.finalexam.entity.TaskStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatusEntity, Integer> {
}
