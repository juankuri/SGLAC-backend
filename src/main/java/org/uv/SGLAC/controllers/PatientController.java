package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.Patient;
import org.uv.SGLAC.services.PatientService;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return patientService.create(patient);
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        return patientService.getById(id);
    }

    @GetMapping
    public List<Patient> getAll() {
        return patientService.getAll();
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.update(id, patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }
}
