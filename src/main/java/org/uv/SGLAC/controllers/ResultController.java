package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.Result;
import org.uv.SGLAC.services.ResultService;
import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping
    public Result create(@RequestBody Result result) {
        return resultService.createResult(result);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return resultService.getResultById(id);
    }

    @GetMapping
    public List<Result> getAll() {
        return resultService.getAllResults();
    }

    @PutMapping
    public Result update(@RequestBody Result result) {
        return resultService.updateResult(result);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        resultService.deleteResult(id);
    }
}
