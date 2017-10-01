package net.avidreder.lead_measure.measure;

public interface MeasureService {
    Measure createNewMeasure(Integer domainId, String measureName);
    Iterable<Measure> getAllMeasures(Integer domainId);
    Measure getMeasureById(Integer domainId, Integer id);
    Measure updateMeasureById(Integer domainId, Integer id, Measure updateMeasure);
    void deleteMeasureById(Integer domainId, Integer id);
}
