package io.proj4.sample.jpa.repository;

import static io.proj4.sample.jpa.TestUtil.format;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.proj4.sample.jpa.JpaUtil;
import io.proj4.sample.jpa.model.Address;
import io.proj4.sample.jpa.model.FuelType;
import io.proj4.sample.jpa.model.Owner;
import io.proj4.sample.jpa.model.Vehicle;
import io.proj4.sample.jpa.repository.owner.OwnerRepository;
import io.proj4.sample.jpa.repository.owner.OwnerRepositoryImpl;

public class OwnerRepositoryTest {
	EntityManager em;
	OwnerRepository repository;
	
	@BeforeEach
	void beforeEach() {
		em = JpaUtil.getEntityManager();
		repository = new OwnerRepositoryImpl(em);
	}
	
	@AfterEach
	void afterEach() {
		em.close();
	}
	
	@Test
	void saveTest() {
		format("save", () -> {
			Owner owner = new Owner();
			owner.setName("Gertrudes de Oliveira Salustiano");
			owner.setBirth(LocalDate.of(1972, 12, 5));
			owner.setEmail("salustianogertrudes@gmail.com");
			owner.getPhones().addAll(Arrays.asList("4455667788", "8877665544"));
			
			Address addr = new Address();
			addr.setAddress("Rua Vila Princesa Isabel");
			addr.setNumber("122");
			addr.setNeighborhood("Sovaco das Cobras");
			addr.setCity("Itajuípe");
			addr.setState("Bahia");
			addr.setOwner(owner);
			owner.setAddress(addr);
			
			Vehicle vhc = new Vehicle();
			vhc.setMake("Ford");
			vhc.setModel("Ranger XLS");
			vhc.setManufactureYear(2013);
			vhc.setModelYear(2014);
			vhc.setPrice(new BigDecimal("74000.00"));
			vhc.setFuelType(FuelType.DIESEL);
			vhc.setOwner(owner);
			owner.setVehicles(Arrays.asList(vhc));
			
			em.getTransaction().begin();
			repository.save(owner);
			em.getTransaction().commit();
			em.clear();
			
			Owner other = em.find(Owner.class, owner.getId());
			assertEquals(owner.getName(), other.getName());
			assertEquals(owner.getBirth(), other.getBirth());
			assertEquals(owner.getEmail(), other.getEmail());
			assertArrayEquals(
					owner.getPhones().toArray(new String[0]), 
					other.getPhones().toArray(new String[0]));
			
			Address otherAddr = other.getAddress();
			assertEquals(addr.getAddress(), otherAddr.getAddress());
			assertEquals(addr.getNumber(), otherAddr.getNumber());
			assertEquals(addr.getNeighborhood(), otherAddr.getNeighborhood());
			assertEquals(addr.getCity(), otherAddr.getCity());
			assertEquals(addr.getState(), otherAddr.getState());
			
			assertEquals(1, other.getVehicles().size());
			Vehicle otherVhc = other.getVehicles().get(0);
			assertEquals(vhc.getMake(), otherVhc.getMake());
			assertEquals(vhc.getModel(), otherVhc.getModel());
			assertEquals(vhc.getManufactureYear(), otherVhc.getManufactureYear());
			assertEquals(vhc.getModelYear(), otherVhc.getModelYear());
			assertEquals(vhc.getFuelType(), otherVhc.getFuelType());
			
			// Clean up
			em.getTransaction().begin();
			em.remove(other.getAddress());
			em.remove(other.getVehicles().get(0));
			em.remove(other);
			em.getTransaction().commit();
		});
	}
	
	@Test
	void findByIdTest() {
		format("findById", () -> {
			Optional<Owner> optional = repository.findById(1L);
			assertTrue(optional.isPresent());
			assertEquals("Joana da Silva Nunes", optional.get().getName());
		});
	}
	
	@Test
	void findByNameTest() {
		format("findByName", () -> {
			List<Owner> owners = repository.findByName("Joana");
			assertEquals(1, owners.size());
			assertEquals(1L, owners.get(0).getId());
		});
	}
	
	@Test
	void findBornBeforeTest() {
		format("findBornBefore", () -> {
			List<Owner> owners = repository
					.findBornBefore(LocalDate.of(1975, 01, 01));
			
			assertEquals(2,  owners.size());
		});
	}
	
	@Test
	void findBornAfterTest() {
		format("findBornAfter", () -> {
			List<Owner> owners = repository
					.findBornAfter(LocalDate.of(1975, 01, 01));
			
			assertEquals(1,  owners.size());
		});
	}
	
	@Test
	void findAllTest() {
		format("findAll", () -> {
			List<Owner> owners = repository.findAll();
			assertEquals(3, owners.size());
		});
	}
}
