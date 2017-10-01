package net.avidreder.lead_measure.measure;

import org.springframework.data.repository.CrudRepository;

public interface MeasureRepository extends CrudRepository<Measure, Integer>{
    Iterable<Measure> findByDomain_Id(Integer id);
    Measure findByIdAndDomain_Id(Integer id, Integer domainId);
    void deleteByIdAndDomain_Id(Integer id, Integer domainId);
}
