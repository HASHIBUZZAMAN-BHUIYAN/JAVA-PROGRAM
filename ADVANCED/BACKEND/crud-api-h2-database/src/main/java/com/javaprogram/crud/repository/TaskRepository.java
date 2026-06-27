package com.javaprogram.crud.repository;

import com.javaprogram.crud.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Spring Data JPA generates SQL automatically from method names
    List<Task> findByCompleted(boolean completed);
    List<Task> findByTitleContainingIgnoreCase(String keyword);
}
