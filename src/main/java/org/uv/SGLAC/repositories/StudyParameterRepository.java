package org.uv.SGLAC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.SGLAC.entities.StudyParameter;

public interface StudyParameterRepository extends JpaRepository<StudyParameter,Long>{
  void deleteByStudyIdAndParameterId(Long studyId, Long parameterId);
}