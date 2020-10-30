package com.crud.crudassignmentsb.repository;

import com.crud.crudassignmentsb.modal.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Integer> {

    @Query(value = "SELECT * FROM services s WHERE s.pac_name=?1 AND s.ser_status='ACT'",
            nativeQuery = true)
    public List<Services> getServicesByPacName(String val);
}
