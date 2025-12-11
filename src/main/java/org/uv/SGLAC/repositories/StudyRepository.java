package org.uv.SGLAC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.SGLAC.entities.Study;

public interface StudyRepository extends JpaRepository<Study,Long>{
    
}

