package org.uv.SGLAC.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.SGLAC.entities.StudyParameter;
import org.uv.SGLAC.repositories.StudyParameterRepository;
import org.uv.SGLAC.services.StudyParameterService;

import java.util.List;

@Service
public class StudyParameterServiceImpl implements StudyParameterService {

    @Autowired
    private StudyParameterRepository studyParameterRepository;

    @Override
    public StudyParameter createStudyParameter(StudyParameter sp) {
        if (sp.getStudy() == null || sp.getParameter() == null) {
            throw new RuntimeException("Debe asignarse un estudio y un parámetro");
        }
        return studyParameterRepository.save(sp);
    }

    @Override
    public StudyParameter getStudyParameterById(Long id) {
        return studyParameterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parametro de estudio no encontrado"));
    }

    @Override
    public List<StudyParameter> getAllStudyParameters() {
        return studyParameterRepository.findAll();
    }

    @Override
    public StudyParameter updateStudyParameter(StudyParameter sp) {
        if (sp.getId() == null) {
            throw new RuntimeException("Id requerido para actualizar");
        }
        return studyParameterRepository.save(sp);
    }

    @Override
    public void deleteStudyParameter(Long id) {
        StudyParameter sp = getStudyParameterById(id);
        studyParameterRepository.delete(sp);
    }
}
