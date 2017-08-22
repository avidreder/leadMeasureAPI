package net.avidreder.lead_measure.domain;

import java.util.List;

public interface DomainDao {
    Domain createDomain(String domainName);
    List<Domain> getAllDomains();
}
