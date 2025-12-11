package org.uv.SGLAC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.SGLAC.entities.Speciality;
import java.util.Optional;

public interface SpecialityRepository extends JpaRepository<Speciality,Long>{
  Optional<Speciality> findByName(String name);
}