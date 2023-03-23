package com.example.myfirstproject.repository;

import com.example.myfirstproject.model.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {


    List<OfferEntity> findAll();
}
