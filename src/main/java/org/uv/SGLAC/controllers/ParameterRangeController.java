package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.ParameterRange;
import org.uv.SGLAC.services.ParameterRangeService;
import java.util.List;

@RestController
@RequestMapping("/parameter-ranges")
public class ParameterRangeController {

    @Autowired
    private ParameterRangeService parameterRangeService;

    @PostMapping
    public ParameterRange create(@RequestBody ParameterRange parameterRange) {
        return parameterRangeService.createParameterRange(parameterRange);
    }

    @GetMapping("/{id}")
    public ParameterRange getById(@PathVariable Long id) {
        return parameterRangeService.getParameterRangeById(id);
    }

    @GetMapping
    public List<ParameterRange> getAll() {
        return parameterRangeService.getAllParameterRanges();
    }

    @PutMapping
    public ParameterRange update(@RequestBody ParameterRange parameterRange) {
        return parameterRangeService.updateParameterRange(parameterRange);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        parameterRangeService.deleteParameterRange(id);
    }
}
