package org.uv.SGLAC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.SGLAC.entities.Result;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Long>{
  List<Result> findByOrderStudyId(Long orderStudyId);
}