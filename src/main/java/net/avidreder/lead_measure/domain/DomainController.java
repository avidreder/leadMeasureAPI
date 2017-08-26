package net.avidreder.lead_measure.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<?> createNewDomain(@RequestBody String domainName) {
        if (domainName == null) {
            return new ResponseEntity("Missing required parameter \"domainName\"",HttpStatus.BAD_REQUEST);
        }
        Domain newDomain = domainService.createDomain(domainName);
        return new ResponseEntity<Domain>(newDomain, HttpStatus.CREATED);
    }
}
