package org.uv.SGLAC.services;

import org.uv.SGLAC.entities.LabTechnician;
import java.util.List;

public interface LabTechnicianService {

    LabTechnician create(LabTechnician labTechnician);
    
    LabTechnician update(Long id, LabTechnician labTechnician);
    
    LabTechnician getById(Long id);
    
    List<LabTechnician> getAll();
    
    void delete(Long id);
}
