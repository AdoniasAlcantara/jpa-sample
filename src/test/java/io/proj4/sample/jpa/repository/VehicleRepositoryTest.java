package io.proj4.sample.jpa.repository;

import static io.proj4.sample.jpa.TestUtil.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.proj4.sample.jpa.JpaUtil;
import io.proj4.sample.jpa.model.FuelType;
import io.proj4.sample.jpa.model.Owner;
import io.proj4.sample.jpa.model.Vehicle;
import io.proj4.sample.jpa.repository.vehicle.VehicleCriteria;
import io.proj4.sample.jpa.repository.vehicle.VehicleRepository;
import io.proj4.sample.jpa.repository.vehicle.VehicleRepositoryImpl;
import io.proj4.sample.jpa.repository.vehicle.VehicleSpecification;

public class VehicleRepositoryTest {
	EntityManager em;
	VehicleRepository repository;
	
	@BeforeEach
	void beforeEach() {
		em = JpaUtil.getEntityManager();
		repository = new VehicleRepositoryImpl(em);
	}
	
	@AfterEach
	void afterEach() {
		em.close();
	}
	
	@Test
	void saveTest() {
		format("save", () -> {
			Vehicle vehicle = newVehicle();
			
			em.getTransaction().begin();
			vehicle = repository.save(vehicle);
			em.getTransaction().commit();
			em.clear();
			
			Vehicle other = em.find(Vehicle.class, vehicle.getId());
			assertEquals(vehicle.getMake(), other.getMake());
			assertEquals(vehicle.getModel(), other.getModel());
			assertEquals(vehicle.getModelYear(), other.getModelYear());
			assertEquals(vehicle.getManufactureYear(), other.getManufactureYear());
			assertEquals(vehicle.getFuelType(), other.getFuelType());
			assertEquals(vehicle.getPrice(), other.getPrice());
			assertEquals(vehicle.getOwner().getId(), other.getOwner().getId());
			
			// Remove so as not to affect the other tests
			em.getTransaction().begin();
			em.remove(other);
			em.getTransaction().commit();
		});
	}
	
	@Test
	void findByIdTest() {
		format("findById", () -> {
			assertTrue(repository.findById(1L).isPresent());
		});
	}
	
	@Test
	void findBySpecificationMakeTest() {
		format("findBySpecificationMake", () -> {
			VehicleCriteria criteria = new VehicleCriteria();
			criteria.setMake("Fiat");
			
			List<Vehicle> vehicles = repository
					.findBySpecification(new VehicleSpecification(criteria));
			
			assertEquals(2, vehicles.size());
		});
	}
	
	@Test
	void findBySpecificationModelTest() {
		format("findBySpecificationModel", () -> {
			VehicleCriteria criteria = new VehicleCriteria();
			criteria.setModel("Fox Xtreme");
			
			List<Vehicle> vehicles = repository
					.findBySpecification(new VehicleSpecification(criteria));
			
			assertEquals(1, vehicles.size());
			assertEquals("Fox Xtreme", vehicles.get(0).getModel());
		});
	}
	
	@Test
	void findBySpecificationMaxPriceTest() {
		format("findBySpecificationMaxPrice", () -> {
			VehicleCriteria criteria = new VehicleCriteria();
			criteria.setMaxPrice(new BigDecimal("40000.00"));
			
			List<Vehicle> vehicles = repository
					.findBySpecification(new VehicleSpecification(criteria));
			
			assertEquals(2, vehicles.size());
		});
	}
	
	@Test
	void findBySpecificationFuelTypeTest() {
		format("indBySpecificationFuelType", () -> {
			VehicleCriteria criteria = new VehicleCriteria();
			criteria.setFuelType(FuelType.FLEXIBLE);
			
			List<Vehicle> vehicles = repository
					.findBySpecification(new VehicleSpecification(criteria));
			
			assertEquals(4, vehicles.size());
		});
	}
	
	@Test
	void findBySpecificationMakeAndMaxPriceTest() {
		format("findBySpecificationMakeAndMaxPrice", () -> {
			VehicleCriteria criteria = new VehicleCriteria();
			criteria.setMake("Fiat");
			criteria.setMaxPrice(new BigDecimal("20000.00"));
			
			List<Vehicle> vehicles = repository
					.findBySpecification(new VehicleSpecification(criteria));
			
			assertEquals(1, vehicles.size());
			assertEquals("Doblo", vehicles.get(0).getModel());
		});
	}
	
	@Test
	void findBySpecificationFromModelYearAndMaxPriceAndFuelTypeTest() {
		format("findBySpecificationFromModelYearAndMaxPriceAndFuelType", () -> {
			VehicleCriteria criteria = new VehicleCriteria();
			criteria.setMaxPrice(new BigDecimal("60000.00"));
			criteria.setFromModelYear(2020);
			criteria.setFuelType(FuelType.GASOLINE);
			
			List<Vehicle> vehicles = repository
					.findBySpecification(new VehicleSpecification(criteria));
			
			assertEquals(2, vehicles.size());
		});
	}
	
	@Test
	void removeTest() {
		format("remove", () -> {
			Vehicle vehicle = newVehicle();
			
			em.getTransaction().begin();
			em.persist(vehicle);
			repository.remove(vehicle);
			em.getTransaction().commit();
			
			assertNull(em.find(Vehicle.class, vehicle.getId()));
		});
	}
	
	@Test
	void removeByIdTest() {		
		format("removeById", () -> {
			Vehicle vehicle = newVehicle();
			
			em.getTransaction().begin();
			em.persist(vehicle);			
			em.getTransaction().commit();
			em.clear();
			
			em.getTransaction().begin();
			repository.removeById(vehicle.getId());
			em.getTransaction().commit();
			
			assertNull(em.find(Vehicle.class, vehicle.getId()));
		});
	}
	
	Vehicle newVehicle() {
		Owner owner = em.find(Owner.class, 1L);
		Vehicle vehicle = new Vehicle();
		vehicle.setMake("Volkswagen");
		vehicle.setModel("Fusca");
		vehicle.setModelYear(1964);
		vehicle.setManufactureYear(1972);
		vehicle.setFuelType(FuelType.GASOLINE);
		vehicle.setPrice(new BigDecimal("2500.00"));
		vehicle.setOwner(owner);
		
		return vehicle;
	}
}
