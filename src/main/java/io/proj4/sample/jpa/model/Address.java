package io.proj4.sample.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String address;
	
	@Column(length = 6)
	private String number;
	
	@Column(length = 25)
	private String neighborhood;
	
	@Column(length = 50, nullable = false)
	private String city;
	
	@Column(length = 20, nullable = false)
	private String state;
	
	@Id
	@OneToOne(optional = false)
	@JoinColumn(name = "ownerId")
	private Owner owner;

	public String getAddress() {
		return address;
	}

	public String getNumber() {
		return number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}
	
	public Owner getOwner() {
		return owner;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Address [")
				.append("address=").append(address)
				.append(", number=").append(number)
				.append(", neighborhood=").append(neighborhood)
				.append(", city=").append(city)
				.append(", state=").append(state)
				.append("]");

		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		return 31 + (int) (owner.getId() ^ (owner.getId() >>> 32));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Address other = (Address) obj;
		
		if (owner.getId() != other.getOwner().getId())
			return false;
		
		return true;
	}
}
