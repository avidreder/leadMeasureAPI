package net.avidreder.lead_measure.measure;

public interface MeasureService {
    Measure createNewMeasure(String measureName);
    Iterable<Measure> getAllMeasures();
    Measure getMeasureById(Integer id);
    Measure updateMeasureById(Integer id, Measure updateMeasure);
    void deleteMeasureById(Integer id);
}
