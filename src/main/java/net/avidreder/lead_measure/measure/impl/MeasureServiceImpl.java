package net.avidreder.lead_measure.measure.impl;
import net.avidreder.lead_measure.measure.Measure;
import net.avidreder.lead_measure.measure.MeasureService;
import net.avidreder.lead_measure.measure.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import net.avidreder.lead_measure.domain.Domain;

@Service
public class MeasureServiceImpl implements MeasureService {

    private MeasureRepository measureRepository;

    @Autowired
    public void setMeasureRepository(MeasureRepository measureRepository) {
        this.measureRepository = measureRepository;
    }

    @Override
    public Measure createNewMeasure(Integer domainId, String measureName) {
        Domain domain = new Domain();
        domain.setId(domainId);
        Measure measure = new Measure(measureName, domain);
        measureRepository.save(measure);
        return measure;
    }

    @Override
    public Iterable<Measure> getAllMeasures(Integer domainId) {
       return measureRepository.findByDomain_Id(domainId);
    }

    @Override
    public Measure getMeasureById(Integer domainId, Integer id) {
        return measureRepository.findByIdAndDomain_Id(id, domainId);
    }

    @Override
    public Measure updateMeasureById(Integer domainId, Integer id, Measure measure) {
        measure.setId(id);
        Domain domain = new Domain();
        domain.setId(domainId);
        measure.setDomain(domain);
        return measureRepository.save(measure);
    }

    @Override
    @Transactional
    public void deleteMeasureById(Integer domainId, Integer id) {
        measureRepository.deleteByIdAndDomain_Id(id, domainId);
    }
}
