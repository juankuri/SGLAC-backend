package org.uv.SGLAC.services;

import org.uv.SGLAC.entities.StudyParameter;
import java.util.List;

public interface StudyParameterService {
    StudyParameter createStudyParameter(StudyParameter sp);
    StudyParameter getStudyParameterById(Long id);
    List<StudyParameter> getAllStudyParameters();
    StudyParameter updateStudyParameter(StudyParameter sp);
    void deleteStudyParameter(Long id);
}
