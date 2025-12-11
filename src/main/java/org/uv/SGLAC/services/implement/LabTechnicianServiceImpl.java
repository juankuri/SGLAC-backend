package org.uv.SGLAC.services.implement;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uv.SGLAC.entities.LabTechnician;
import org.uv.SGLAC.repositories.LabTechnicianRepository;
import org.uv.SGLAC.services.LabTechnicianService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LabTechnicianServiceImpl implements LabTechnicianService {

    private final LabTechnicianRepository labTechnicianRepository;

    public LabTechnicianServiceImpl(LabTechnicianRepository labTechnicianRepository) {
        this.labTechnicianRepository = labTechnicianRepository;
    }

    @Override
    @Transactional
    public LabTechnician create(LabTechnician labTechnician) {
        if (labTechnician.getUser() == null) {
            throw new IllegalArgumentException("El técnico del laboratorio debe tener un usuario asignado.");
        }
        
        return labTechnicianRepository.save(labTechnician);
    }

    @Override
    @Transactional
    public LabTechnician update(Long id, LabTechnician labTechnician) {
        LabTechnician existing = labTechnicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Técnico de laboratorio no encontrado: " + id));
        
        existing.setUser(labTechnician.getUser());
        
        return labTechnicianRepository.save(existing);
    }

    @Override
    public LabTechnician getById(Long id) {
        return labTechnicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Técnico de laboratorio no encontrado: " + id));
    }

    @Override
    public List<LabTechnician> getAll() {
        return labTechnicianRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!labTechnicianRepository.existsById(id)) {
            throw new EntityNotFoundException("No existe el técnico de laboratorio con id: " + id);
        }
        labTechnicianRepository.deleteById(id);
    }
}
