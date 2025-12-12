package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.dtos.LabTechnicianCreateDTO;
import org.uv.SGLAC.entities.LabTechnician;
import org.uv.SGLAC.services.LabTechnicianService;

import java.util.List;

@RestController
@RequestMapping("/lab-technicians")
public class LabTechnicianController {

    @Autowired
    private LabTechnicianService labTechnicianService;

    @PostMapping
    public LabTechnician create(@RequestBody LabTechnicianCreateDTO labTechnician) {
        return labTechnicianService.create(labTechnician);
    }

    @GetMapping("/{id}")
    public LabTechnician getById(@PathVariable Long id) {
        return labTechnicianService.getById(id);
    }

    @GetMapping
    public List<LabTechnician> getAll() {
        return labTechnicianService.getAll();
    }

    @PutMapping("/{id}")
    public LabTechnician update(@PathVariable Long id, @RequestBody LabTechnician labTechnician) {
        return labTechnicianService.update(id, labTechnician);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        labTechnicianService.delete(id);
    }
}
