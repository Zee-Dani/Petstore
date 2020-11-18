package com.petstore.petstore.data.repository;

import com.petstore.petstore.data.model.Gender;
import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;


import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
    }

    // Test that we can save a pet to the database
    @Test

    @Rollback(value = false)
    public void whenPetIsSaved_thenReturnAPetId() {

        // step 1: Create an instance of a pet

        Pet pet = new Pet();
        pet.setName("jack");
        pet.setAge(4);
        pet.setBreed("Dog");
        pet.setColour("Black");
        pet.setPetSex(Gender.MALE);
        log.info("pet instance before saving --> {}", pet);

        // call repoository save method
        pet = petRepository.save(pet);
        assertThat(pet.getId()).isNotNull();
        log.info("pet instance after saving --> {}", pet);


        Pet pat = new Pet();
        pat.setName("lilo");
        pat.setAge(3);
        pat.setBreed("cat");
        pat.setColour("red");
        pat.setPetSex(Gender.FEMALE);
        petRepository.save(pat);
        //  assertThat(pat.getName().isNotNull());

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void whenStoreIsMapped_thenForeignkeyIsPresent() {


        //  create a pet
        Pet pet = new Pet();
        pet.setName("jack");
        pet.setAge(4);
        pet.setBreed("Dog");
        pet.setColour("Black");
        pet.setPetSex(Gender.MALE);

        log.info("pet instance before saving --> {}", pet);

        // create a store
        Store store = new Store();
        store.setName("Pet Sellers");
        store.setLocation("Yaba");
        store.setContactNo("09876543210");

        // map pet to store
        pet.setStore(store);

        // save pet
        petRepository.save(pet);
        log.info("pet instance before saving --> {}", pet);
        log.info("store instance before saving --> {}", store);

        // assert
        assertThat(pet.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();
        assertThat(pet.getStore()).isNotNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void whenIAddPetsToASTore_thenICanFetchAListOfPetsFromStore() {

        // create a  store
        Store store = new Store();
        store.setName("Pet Sellers");
        store.setLocation("Yaba");
        store.setContactNo("00000067");
        // create two pets
        Pet joll = new Pet();
        joll.setName("joll");
        joll.setAge(4);
        joll.setBreed("pig");
        joll.setColour("Blue");
        joll.setPetSex(Gender.MALE);
        joll.setStore(store);

        Pet sally = new Pet();
        sally.setName("lilo");
        sally.setAge(3);
        sally.setBreed("cat");
        sally.setColour("red");
        sally.setPetSex(Gender.FEMALE);
        sally.setStore(store);

        //  assertThat(pat.getName().isNotNull());

        log.info("pet instances before saving --> {}", joll,sally);

        // map pet to store
        store.addPets(joll);
        store.addPets(sally);

        // save store
        storeRepository.save(store);

        log.info("Store instance after saving --> {}", store);


        // assert for pet id's
        // assert for store id

    }

    @Test
    public void whenFindAllPetIdIsCalled_thenReturnPetInStore(){

        // find pets from store
        List<Pet> savedPets = petRepository.findAll();
        log.info("Fetched pets list from db --> {}", savedPets);
        //assert that pets exist
        assertThat(savedPets).isNotEmpty();
        assertThat(savedPets.size()).isEqualTo(4);
    }

    @Test
    public void updateExistingPetDetailsTest(){
        // fetch a pet
        Pet jill = petRepository.findById(31).orElse(null);
        log.info("Pet object retrieved from database --> {}",jill);


        // assert the field
        assertThat(jill).isNotNull();
        assertThat(jill.getColour()).isEqualTo("blue");
        // update pet field
        jill.setColour("purple");
        // save pet
        petRepository.save(jill);
        log.info("After updating pet object --> {}",jill);
        // assert that updated field has changed
        assertThat(jill.getColour()).isEqualTo("purple");


    }

    @Test
    public void whenIdeletePetFromDatabase_thenPetIsDeleted(){

        assertThat(petRepository.existsById(31)).isTrue();
        petRepository.deleteById(31);
        assertThat(petRepository.existsById(31)).isFalse();


        }
}



