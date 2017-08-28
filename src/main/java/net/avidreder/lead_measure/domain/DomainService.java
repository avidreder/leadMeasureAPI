package net.avidreder.lead_measure.domain;

public interface DomainService {
    Domain createNewDomain(String domainName);
    Iterable<Domain> getAllDomains();
    Domain getDomainById(Integer id);
    Domain updateDomainById(Integer id, Domain updateDomain);
    void deleteDomainById(Integer id);
}
