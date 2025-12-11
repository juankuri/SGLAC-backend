package org.uv.SGLAC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.SGLAC.entities.Patient;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long>{
  Optional<Patient> findByUserId(Long userId);
    Optional<Patient> findByRecordNumber(String recordNumber);
}