package net.avidreder.lead_measure.domain.impl;
import net.avidreder.lead_measure.domain.Domain;
import net.avidreder.lead_measure.domain.DomainDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static net.avidreder.lead_measure.Application.session;

@Service
public class DomainDaoImpl implements DomainDao{

    @Override
    public Domain createDomain(String domainName) {
        Domain domain = new Domain(domainName);
        session.beginTransaction();
        session.save(domain);
        session.getTransaction().commit();
        return domain;
    }

    @Override
    public List<Domain> getAllDomains() {
        session.beginTransaction();
        List<Domain> domains = new ArrayList<>();
        domains = session.createQuery("from Domain").list();
        session.getTransaction().commit();
        return domains;
    }
}
