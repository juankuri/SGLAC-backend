package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.OrderStudy;
import org.uv.SGLAC.services.OrderStudyService;

import java.util.List;

@RestController
@RequestMapping("/order-studies")
public class OrderStudyController {

    @Autowired
    private OrderStudyService orderStudyService;

    @PostMapping
    public OrderStudy save(@RequestBody OrderStudy orderStudy) {
        return orderStudyService.saveOrderStudy(orderStudy);
    }

    @GetMapping("/{id}")
    public OrderStudy getById(@PathVariable Long id) {
        return orderStudyService.getOrderStudyById(id);
    }

    @GetMapping
    public List<OrderStudy> getAll() {
        return orderStudyService.getAllOrderStudies();
    }

    @PutMapping("/{id}")
    public OrderStudy update(@PathVariable Long id, @RequestBody OrderStudy orderStudy) {
        return orderStudyService.updateOrderStudy(id, orderStudy);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderStudyService.deleteOrderStudy(id);
    }
}
