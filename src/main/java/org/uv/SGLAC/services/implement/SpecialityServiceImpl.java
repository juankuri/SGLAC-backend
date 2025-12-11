package org.uv.SGLAC.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.SGLAC.entities.Speciality;
import org.uv.SGLAC.repositories.SpecialityRepository;
import org.uv.SGLAC.services.SpecialityService;

import java.util.List;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public Speciality createSpeciality(Speciality speciality) {
        if (speciality.getName() == null || speciality.getName().isEmpty()) {
            throw new RuntimeException("El nombre de la especialidad no puede estar vacío");
        }
        return specialityRepository.save(speciality);
    }

    @Override
    public Speciality getSpecialityById(Long id) {
        return specialityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
    }

    @Override
    public List<Speciality> getAllSpecialities() {
        return specialityRepository.findAll();
    }

    @Override
    public Speciality updateSpeciality(Speciality speciality) {
        if (speciality.getId() == null) {
            throw new RuntimeException("Id requerido para actualizar");
        }
        return specialityRepository.save(speciality);
    }

    @Override
    public void deleteSpeciality(Long id) {
        Speciality s = getSpecialityById(id);
        specialityRepository.delete(s);
    }
}
