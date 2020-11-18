package com.petstore.petstore.data.repository;

import com.petstore.petstore.data.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {


}


