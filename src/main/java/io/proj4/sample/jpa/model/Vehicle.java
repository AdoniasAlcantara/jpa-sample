package io.proj4.sample.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicleId")
	private Long id;

	@Column(length = 25, nullable = false)
	private String make;
	
	@Column(length = 25, nullable = false)
	private String model;
	
	@Column(nullable = false)
	private Integer modelYear;
	
	@Column(nullable = false)
	private Integer manufactureYear;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FuelType fuelType;
	
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal price;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ownerId")
	private Owner owner;

	public Long getId() {
		return id;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public Integer getModelYear() {
		return modelYear;
	}

	public Integer getManufactureYear() {
		return manufactureYear;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setModelYear(Integer modelYear) {
		this.modelYear = modelYear;
	}

	public void setManufactureYear(Integer manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Vehicle [")
				.append("id=").append(id)
				.append(", make=").append(make)
				.append(", model=").append(model)
				.append(", modelYear=").append(modelYear)
				.append(", manufactureYear=").append(manufactureYear)
				.append(", fuelType=").append(fuelType)
				.append(", price=").append(price)
				.append("]");
		
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return 31 + (int) (id ^ (id >>> 32));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Vehicle other = (Vehicle) obj;
		
		if (id != other.id)
			return false;
		
		return true;
	}
}
