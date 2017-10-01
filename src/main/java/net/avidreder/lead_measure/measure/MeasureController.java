package net.avidreder.lead_measure.measure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
public class MeasureController {
    private MeasureService measureService;

    @Autowired
    public void setMeasureService(MeasureService measureService) {
        this.measureService = measureService;
    }

    @RequestMapping(value = "/measures", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Measure>> listAllmeasures() {
        Iterable<Measure> measures = measureService.getAllMeasures();
        return new ResponseEntity<Iterable<Measure>>(measures, HttpStatus.OK);
    }

    @RequestMapping(value = "/measures", method = RequestMethod.POST)
    public ResponseEntity<?> createNewMeasure(@RequestParam String measureName) {
        if (measureName == null) {
            return new ResponseEntity("Missing required parameter \"measureName\"",HttpStatus.BAD_REQUEST);
        }
        Measure newMeasure = measureService.createNewMeasure(measureName);
        return new ResponseEntity<Measure>(newMeasure, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/measure/{id}", method = RequestMethod.GET)
    public ResponseEntity<Measure> getmeasureById(@PathVariable("id") Integer id) {
        Measure measure = measureService.getMeasureById(id);
        return new ResponseEntity<Measure>(measure, HttpStatus.OK);
    }

    @RequestMapping(value = "/measure/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatemeasureById(@PathVariable("id") Integer id, @RequestBody Measure newMeasure) {
        if (newMeasure == null) {
            return new ResponseEntity("Missing required parameter \"newMeasure\"",HttpStatus.BAD_REQUEST);
        }
        newMeasure.setId(id);
        Measure updatedmeasure = measureService.updateMeasureById(id, newMeasure);
        return new ResponseEntity<Measure>(updatedmeasure, HttpStatus.OK);
    }

    @RequestMapping(value = "/measure/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletemeasureByID(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity("Missing required parameter \"id\"",HttpStatus.BAD_REQUEST);
        }
        measureService.deleteMeasureById(id);
        return new ResponseEntity<>("Measure deleted", HttpStatus.OK);
    }
}
