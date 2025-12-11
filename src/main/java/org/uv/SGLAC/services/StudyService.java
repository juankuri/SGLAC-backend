package org.uv.SGLAC.services;

import org.uv.SGLAC.entities.Study;
import java.util.List;

public interface StudyService {
    Study createStudy(Study study);
    Study getStudyById(Long id);
    List<Study> getAllStudies();
    Study updateStudy(Study study);
    void deleteStudy(Long id);
}
