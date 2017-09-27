package net.avidreder.lead_measure.measure.impl;
import net.avidreder.lead_measure.measure.Measure;
import net.avidreder.lead_measure.measure.MeasureService;
import net.avidreder.lead_measure.measure.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureServiceImpl implements MeasureService {

    private MeasureRepository measureRepository;

    @Autowired
    public void setMeasureRepository(MeasureRepository measureRepository) {
        this.measureRepository = measureRepository;
    }

    @Override
    public Measure createNewMeasure(String measureName) {
        Measure measure = new Measure(measureName);
        measureRepository.save(measure);
        return measure;
    }

    @Override
    public Iterable<Measure> getAllMeasures() {
       return measureRepository.findAll();
    }

    @Override
    public Measure getMeasureById(Integer id) {
        return measureRepository.findOne(id);
    }

    @Override
    public Measure updateMeasureById(Integer id, Measure measure) {
        measure.setId(id);
        return measureRepository.save(measure);
    }

    @Override
    public void deleteMeasureById(Integer id) {
        measureRepository.delete(id);
    }
}
