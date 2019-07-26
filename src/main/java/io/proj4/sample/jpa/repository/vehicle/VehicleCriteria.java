package io.proj4.sample.jpa.repository.vehicle;

import java.math.BigDecimal;

import io.proj4.sample.jpa.model.FuelType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VehicleCriteria {
	private String make;
	private String model;
	private int fromModelYear;
	private BigDecimal maxPrice;
	private FuelType fuelType;
}
