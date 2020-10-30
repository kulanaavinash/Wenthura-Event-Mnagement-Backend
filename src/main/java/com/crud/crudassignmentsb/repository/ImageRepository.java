package com.crud.crudassignmentsb.repository;

import com.crud.crudassignmentsb.modal.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findById(int id);
}
