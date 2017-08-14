package com.lead.measure.api.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Measure implements Serializable {

	public Measure() {

	};

  public Measure(String measureName) {
    this.measureName = measureName;
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String measureName;

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
