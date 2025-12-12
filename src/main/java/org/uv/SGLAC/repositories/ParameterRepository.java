package org.uv.SGLAC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.SGLAC.entities.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter,Long>{
    
}
