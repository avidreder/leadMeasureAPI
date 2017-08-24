package net.avidreder.lead_measure;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import static net.avidreder.lead_measure.Application.domainDao;
import net.avidreder.lead_measure.domain.Domain;

@RestController
public class DomainController {
    @RequestMapping(value = "/domains", method = RequestMethod.GET)
    public ResponseEntity<List<Domain>> listAllDomains() {
        List<Domain> domains = domainDao.getAllDomains();
        return new ResponseEntity<List<Domain>>(domains, HttpStatus.OK);
    }

    @RequestMapping(value = "/domains", method = RequestMethod.POST)
    public ResponseEntity<?> createNewDomain(@RequestBody String domainName) {
        if (domainName == null) {
            return new ResponseEntity("Missing required parameter \"domainName\"",HttpStatus.BAD_REQUEST);
        }
        Domain newDomain = domainDao.createDomain(domainName);
        return new ResponseEntity<Domain>(newDomain, HttpStatus.CREATED);
    }
}
