package net.avidreder.lead_measure.domain;

import java.util.List;
import org.springframework.stereotype.Service;

public interface DomainDao {
    Domain createDomain(String domainName);
    List<Domain> getAllDomains();
}
