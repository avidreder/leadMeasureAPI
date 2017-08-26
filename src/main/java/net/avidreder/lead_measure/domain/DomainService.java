package net.avidreder.lead_measure.domain;

public interface DomainService {
    Domain createDomain(String domainName);
    Iterable<Domain> getAllDomains();
}
