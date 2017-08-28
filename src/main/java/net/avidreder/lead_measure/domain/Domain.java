package net.avidreder.lead_measure.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Domain implements Serializable {

    public Domain() {
        this.domainName = "";
    }

    public Domain(String domainName) {
        this.domainName = domainName;
    }

    @Id
    @Column(name = "domain_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String domainName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

}
