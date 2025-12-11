package org.uv.SGLAC.services;

import org.uv.SGLAC.entities.Speciality;
import java.util.List;

public interface SpecialityService {
    Speciality createSpeciality(Speciality speciality);
    Speciality getSpecialityById(Long id);
    List<Speciality> getAllSpecialities();
    Speciality updateSpeciality(Speciality speciality);
    void deleteSpeciality(Long id);
}
