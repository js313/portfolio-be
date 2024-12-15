package com.jeenit.portfolio.repository;

import com.jeenit.portfolio.model.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTypeRepository extends JpaRepository<ProjectType, Integer> {
    boolean existsByName(String name);
}
