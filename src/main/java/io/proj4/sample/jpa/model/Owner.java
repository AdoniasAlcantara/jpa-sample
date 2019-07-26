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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
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
}
