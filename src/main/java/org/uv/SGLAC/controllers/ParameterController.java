package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.Parameter;
import org.uv.SGLAC.services.ParameterService;
import java.util.List;

@RestController
@RequestMapping("/parameters")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    @PostMapping
    public Parameter create(@RequestBody Parameter parameter) {
        return parameterService.createParameter(parameter);
    }

    @GetMapping("/{id}")
    public Parameter getById(@PathVariable Long id) {
        return parameterService.getParameterById(id);
    }

    @GetMapping
    public List<Parameter> getAll() {
        return parameterService.getAllParameters();
    }

    @PutMapping
    public Parameter update(@RequestBody Parameter parameter) {
        return parameterService.updateParameter(parameter);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        parameterService.deleteParameter(id);
    }
}
