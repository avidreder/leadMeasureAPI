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

    @RequestMapping(value = "/{domainId}/measures", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Measure>> listAllmeasures(@PathVariable("domainId") Integer domainId) {
        if (domainId == null) {
            return new ResponseEntity("Missing required parameter \"domainId\"",HttpStatus.BAD_REQUEST);
        }
        Iterable<Measure> measures = measureService.getAllMeasures(domainId);
        return new ResponseEntity<Iterable<Measure>>(measures, HttpStatus.OK);
    }

    @RequestMapping(value = "/{domainId}/measures", method = RequestMethod.POST)
    public ResponseEntity<?> createNewMeasure(@PathVariable("domainId") Integer domainId, @RequestParam String measureName) {
        if (domainId == null) {
            return new ResponseEntity("Missing required parameter \"domainId\"",HttpStatus.BAD_REQUEST);
        }
        Measure newMeasure = measureService.createNewMeasure(domainId, measureName);
        return new ResponseEntity<Measure>(newMeasure, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{domainId}/measure/{id}", method = RequestMethod.GET)
    public ResponseEntity<Measure> getmeasureById(@PathVariable("domainId") Integer domainId, @PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity("Missing required parameter \"id\"",HttpStatus.BAD_REQUEST);
        }
        if (domainId == null) {
            return new ResponseEntity("Missing required parameter \"domainId\"",HttpStatus.BAD_REQUEST);
        }
        Measure measure = measureService.getMeasureById(domainId, id);
        return new ResponseEntity<Measure>(measure, HttpStatus.OK);
    }

    @RequestMapping(value = "/{domainId}/measure/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatemeasureById(@PathVariable("domainId") Integer domainId, @PathVariable("id") Integer id, @RequestBody Measure newMeasure) {
        if (id == null) {
            return new ResponseEntity("Missing required parameter \"id\"",HttpStatus.BAD_REQUEST);
        }
        if (domainId == null) {
            return new ResponseEntity("Missing required parameter \"domainId\"",HttpStatus.BAD_REQUEST);
        }
        Measure updatedmeasure = measureService.updateMeasureById(domainId, id, newMeasure);
        return new ResponseEntity<Measure>(updatedmeasure, HttpStatus.OK);
    }

    @RequestMapping(value = "/{domainId}/measure/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMeasureByID(@PathVariable("domainId") Integer domainId, @PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity("Missing required parameter \"id\"",HttpStatus.BAD_REQUEST);
        }
        if (domainId == null) {
            return new ResponseEntity("Missing required parameter \"domainId\"",HttpStatus.BAD_REQUEST);
        }
        measureService.deleteMeasureById(domainId, id);
        return new ResponseEntity<>("Measure deleted", HttpStatus.OK);
    }
}
