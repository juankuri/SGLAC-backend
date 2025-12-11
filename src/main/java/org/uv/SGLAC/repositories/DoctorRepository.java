package org.uv.SGLAC.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.SGLAC.entities.Doctor;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long>{
  Optional<Doctor> findByUserId(Long userId);
}
