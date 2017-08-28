package net.avidreder.lead_measure.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
public class DomainController {
    private DomainService domainService;

    @Autowired
    public void setDomainService(DomainService domainService) {
        this.domainService = domainService;
    }

    @RequestMapping(value = "/domains", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Domain>> listAllDomains() {
        Iterable<Domain> domains = domainService.getAllDomains();
        return new ResponseEntity<Iterable<Domain>>(domains, HttpStatus.OK);
    }

    @RequestMapping(value = "/domains", method = RequestMethod.POST)
    public ResponseEntity<?> createNewDomain(@RequestParam String domainName) {
        if (domainName == null) {
            return new ResponseEntity("Missing required parameter \"domainName\"",HttpStatus.BAD_REQUEST);
        }
        Domain newDomain = domainService.createNewDomain(domainName);
        return new ResponseEntity<Domain>(newDomain, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/domain/{id}", method = RequestMethod.GET)
    public ResponseEntity<Domain> getDomainById(@PathVariable("id") Integer id) {
        Domain domain = domainService.getDomainById(id);
        return new ResponseEntity<Domain>(domain, HttpStatus.OK);
    }

    @RequestMapping(value = "/domain/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDomainById(@PathVariable("id") Integer id, @RequestBody Domain newDomain) {
        if (newDomain == null) {
            return new ResponseEntity("Missing required parameter \"newDomain\"",HttpStatus.BAD_REQUEST);
        }
        newDomain.setId(id);
        Domain updatedDomain = domainService.updateDomainById(id, newDomain);
        return new ResponseEntity<Domain>(updatedDomain, HttpStatus.OK);
    }

    @RequestMapping(value = "/domain/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDomainByID(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity("Missing required parameter \"id\"",HttpStatus.BAD_REQUEST);
        }
        domainService.deleteDomainById(id);
        return new ResponseEntity<>("Domain deleted", HttpStatus.OK);
    }
}
