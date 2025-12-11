package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.StudyParameter;
import org.uv.SGLAC.services.StudyParameterService;
import java.util.List;

@RestController
@RequestMapping("/study-parameters")
public class StudyParameterController {

    @Autowired
    private StudyParameterService studyParameterService;

    @PostMapping
    public StudyParameter create(@RequestBody StudyParameter sp) {
        return studyParameterService.createStudyParameter(sp);
    }

    @GetMapping("/{id}")
    public StudyParameter getById(@PathVariable Long id) {
        return studyParameterService.getStudyParameterById(id);
    }

    @GetMapping
    public List<StudyParameter> getAll() {
        return studyParameterService.getAllStudyParameters();
    }

    @PutMapping
    public StudyParameter update(@RequestBody StudyParameter sp) {
        return studyParameterService.updateStudyParameter(sp);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studyParameterService.deleteStudyParameter(id);
    }
}
