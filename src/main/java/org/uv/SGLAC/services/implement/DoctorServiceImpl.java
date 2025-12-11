package org.uv.SGLAC.services.implement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uv.SGLAC.entities.*;
import org.uv.SGLAC.repositories.*;
import org.uv.SGLAC.services.DoctorService;
import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
  @Autowired private DoctorRepository doctorRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private SpecialityRepository specialityRepository;

  @Override
  public Doctor createDoctor(Doctor d){
    if (d.getUser()==null || d.getUser().getId()==null) throw new RuntimeException("User requerido");
    if (doctorRepository.findByUserId(d.getUser().getId()).isPresent()) throw new RuntimeException("Este usuario ya es un doctor");
    User u = userRepository.findById(d.getUser().getId()).orElseThrow(()->new RuntimeException("User no encontrado"));
    Speciality sp = specialityRepository.findById(d.getSpeciality().getId()).orElseThrow(()->new RuntimeException("Especialidad no encontrada"));
    d.setUser(u);
    d.setSpeciality(sp);
    if (d.getProfessionalId()==null) throw new RuntimeException("Id profesional requerido");
    return doctorRepository.save(d);
  }

  @Override public Doctor getDoctor(Long id){ return doctorRepository.findById(id).orElseThrow(()->new RuntimeException("Doctor no encontrado")); }
  @Override public List<Doctor> getAllDoctors(){ return doctorRepository.findAll(); }

  @Override
  public Doctor updateDoctor(Long id, Doctor d){
    Doctor ex = getDoctor(id);
    if (d.getProfessionalId()!=null) ex.setProfessionalId(d.getProfessionalId());
    if (d.getSpeciality()!=null && d.getSpeciality().getId()!=null){
      Speciality s = specialityRepository.findById(d.getSpeciality().getId()).orElseThrow(()->new RuntimeException("Especialidad no encontrada"));
      ex.setSpeciality(s);
    }
    return doctorRepository.save(ex);
  }
  @Override public void deleteDoctor(Long id){ if (!doctorRepository.existsById(id)) throw new RuntimeException("Doctor no encontrado"); doctorRepository.deleteById(id); }
}
