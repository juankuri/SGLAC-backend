package org.uv.SGLAC.repositories;
import org.uv.SGLAC.entities.LabTechnician;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;   

public interface LabTechnicianRepository extends JpaRepository<LabTechnician,Long>{
  Optional<LabTechnician> findByUserId(Long userId);
}
