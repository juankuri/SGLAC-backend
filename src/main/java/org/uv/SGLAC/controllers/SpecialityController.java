package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.Speciality;
import org.uv.SGLAC.services.SpecialityService;
import java.util.List;

@RestController
@RequestMapping("/specialities")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @PostMapping
    public Speciality create(@RequestBody Speciality speciality) {
        return specialityService.createSpeciality(speciality);
    }

    @GetMapping("/{id}")
    public Speciality getById(@PathVariable Long id) {
        return specialityService.getSpecialityById(id);
    }

    @GetMapping
    public List<Speciality> getAll() {
        return specialityService.getAllSpecialities();
    }

    @PutMapping
    public Speciality update(@RequestBody Speciality speciality) {
        return specialityService.updateSpeciality(speciality);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        specialityService.deleteSpeciality(id);
    }
}
