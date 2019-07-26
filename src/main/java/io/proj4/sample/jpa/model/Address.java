package io.proj4.sample.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
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
