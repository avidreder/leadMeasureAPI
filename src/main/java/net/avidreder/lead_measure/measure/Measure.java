package net.avidreder.lead_measure.measure;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Measure implements Serializable {

    public Measure() {
        this.measureName = "";
    }

    public Measure(String measureName) {
        this.measureName = measureName;
    }

    @Id
    @Column(name = "measure_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String measureName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeasureName() {
        return this.measureName;
    }

    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }

}
