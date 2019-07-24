package io.proj4.sample.jpa.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
public class Owner implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ownerId")
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDate birth;
	
	@OneToOne(mappedBy = "owner", optional = false, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private Address address;
	
	@Column(length = 50)
	private String email;
	
	@ElementCollection
	@CollectionTable(name = "OwnerPhone", joinColumns = @JoinColumn(name = "ownerId"))
	@Column(name = "number", columnDefinition = "CHAR(20)", nullable = false)
	private Set<String> phones = new HashSet<>();
	
	@OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST})
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public Address getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Owner [")
				.append("id=").append(id)
				.append(", name=").append(name)
				.append(", birth=").append(birth)
				.append(", address=").append(address)
				.append(", email=").append(email)
				.append(", phones=").append(phones)
				.append(", vehicles=").append(vehicles)
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
		
		Owner other = (Owner) obj;
		
		if (id != other.id)
			return false;
		
		return true;
	}
}
