package com.example.myfirstproject.repository;

import com.example.myfirstproject.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
}
