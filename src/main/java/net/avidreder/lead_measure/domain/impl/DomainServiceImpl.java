package net.avidreder.lead_measure.domain.impl;
import net.avidreder.lead_measure.domain.Domain;
import net.avidreder.lead_measure.domain.DomainService;
import net.avidreder.lead_measure.domain.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainServiceImpl implements DomainService {

    private DomainRepository domainRepository;

    @Autowired
    public void setDomainRepository(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    public Domain createNewDomain(String domainName) {
        Domain domain = new Domain(domainName);
        domainRepository.save(domain);
        return domain;
    }

    @Override
    public Iterable<Domain> getAllDomains() {
       return domainRepository.findAll();
    }

    @Override
    public Domain getDomainById(Integer id) {
        return domainRepository.findOne(id);
    }

    @Override
    public Domain updateDomainById(Integer id, Domain domain) {
        domain.setId(id);
        return domainRepository.save(domain);
    }

    @Override
    public void deleteDomainById(Integer id) {
        domainRepository.delete(id);
    }
}
