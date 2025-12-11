package org.uv.SGLAC.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.SGLAC.entities.Study;
import org.uv.SGLAC.repositories.StudyRepository;
import org.uv.SGLAC.services.StudyService;
import java.util.List;

@Service
public class StudyServiceImpl implements StudyService {

    @Autowired
    private StudyRepository studyRepository;

    @Override
    public Study createStudy(Study study) {
        if (study.getName() == null || study.getName().isEmpty()) {
            throw new RuntimeException("El nombre del estudio no puede estar vacío");
        }
        return studyRepository.save(study);
    }

    @Override
    public Study getStudyById(Long id) {
        return studyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudio no encontrado"));
    }

    @Override
    public List<Study> getAllStudies() {
        return studyRepository.findAll();
    }

    @Override
    public Study updateStudy(Study study) {
        if (study.getId() == null) {
            throw new RuntimeException("ID requerido para actualizar");
        }
        return studyRepository.save(study);
    }

    @Override
    public void deleteStudy(Long id) {
        Study study = getStudyById(id);
        studyRepository.delete(study);
    }
}
