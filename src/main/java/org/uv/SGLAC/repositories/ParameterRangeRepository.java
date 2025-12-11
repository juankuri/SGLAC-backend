package org.uv.SGLAC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.SGLAC.entities.ParameterRange;
import java.util.List;

public interface ParameterRangeRepository extends JpaRepository<ParameterRange,Long>{
  List<ParameterRange> findByParameterId(Long parameterId);
}
