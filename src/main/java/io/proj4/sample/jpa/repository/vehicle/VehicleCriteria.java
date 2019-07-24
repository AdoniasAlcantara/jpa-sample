package io.proj4.sample.jpa.repository.vehicle;

import java.math.BigDecimal;

import io.proj4.sample.jpa.model.FuelType;

public class VehicleCriteria {
	private String make;
	private String model;
	private int fromModelYear;
	private BigDecimal maxPrice;
	private FuelType fuelType;

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getFromModelYear() {
		return fromModelYear;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setFromModelYear(int fromYear) {
		this.fromModelYear = fromYear;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
}
