package com.example.myfirstproject.repository;

import com.example.myfirstproject.model.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository  extends JpaRepository<OfferEntity,Long> {

}
