package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.MeasurementDao;
import com.fmg.gmf_core.entitys.Ingredient;
import com.fmg.gmf_core.entitys.Measurement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementDao measurementDao;

    public MeasurementController(MeasurementDao measurementDao) {
        this.measurementDao = measurementDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Measurement>> getAllMeasurement() {
        return ResponseEntity.ok(measurementDao.findAll());
    }
    @PostMapping("/new")
    public ResponseEntity<Measurement> newMeasurement(@Valid @RequestBody Measurement measurement, @RequestHeader("Authorization") String authorizationHeader){
        measurementDao.save(measurement);
        return ResponseEntity.ok(measurement);
    }
}
