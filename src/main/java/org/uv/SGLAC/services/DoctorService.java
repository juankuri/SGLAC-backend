package org.uv.SGLAC.services;
import org.uv.SGLAC.entities.Doctor;
import java.util.List;

public interface DoctorService {
  Doctor createDoctor(Doctor d);
  Doctor getDoctor(Long id);
  List<Doctor> getAllDoctors();
  Doctor updateDoctor(Long id, Doctor d);
  void deleteDoctor(Long id);
}
