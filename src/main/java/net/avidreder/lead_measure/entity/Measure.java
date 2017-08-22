package net.avidreder.lead_measure.entity;

import net.avidreder.lead_measure.domain.Domain;

import java.io.Serializable;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Measure implements Serializable {

//    public Measure() {
//
//    }

    public Measure(String measureName, Domain domainId) {
        this.measureName = measureName;
        this.domainId = domainId;
    }

    @Id
    @Column(name = "measure_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String measureName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domain_id")
    private Domain domainId;

    public Domain getDomainId() {
        return domainId;
    }

    public void setDomainId(Domain domainId) {
        this.domainId = domainId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeasureName() {
        return measureName;
    }

    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }

}
