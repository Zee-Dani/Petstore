package com.petstore.petstore.data.repository;

import com.petstore.petstore.data.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreRepository extends JpaRepository <Store, Integer>{
}
