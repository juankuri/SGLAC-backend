package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.Study;
import org.uv.SGLAC.services.StudyService;
import java.util.List;

@RestController
@RequestMapping("/studies")
public class StudyController {

    @Autowired
    private StudyService studyService;

    @PostMapping
    public Study create(@RequestBody Study study) {
        return studyService.createStudy(study);
    }

    @GetMapping("/{id}")
    public Study getById(@PathVariable Long id) {
        return studyService.getStudyById(id);
    }

    @GetMapping
    public List<Study> getAll() {
        return studyService.getAllStudies();
    }

    @PutMapping
    public Study update(@RequestBody Study study) {
        return studyService.updateStudy(study);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studyService.deleteStudy(id);
    }
}
