package org.uv.SGLAC.services;
import org.uv.SGLAC.dtos.DoctorCreateDTO;
import org.uv.SGLAC.entities.Doctor;
import java.util.List;

public interface DoctorService {
    Doctor create(DoctorCreateDTO dto);
    Doctor update(Long id, Doctor doctor);
    Doctor getById(Long id);
    List<Doctor> getAll();
    void delete(Long id);
}
