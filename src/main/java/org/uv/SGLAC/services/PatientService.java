package org.uv.SGLAC.services;

import org.uv.SGLAC.entities.Patient;
import java.util.List;

public interface PatientService {

    Patient create(Patient patient);

    Patient update(Long id, Patient patient);

    Patient getById(Long id);

    List<Patient> getAll();

    void delete(Long id);
}
