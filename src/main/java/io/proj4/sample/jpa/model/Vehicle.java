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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
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
}
