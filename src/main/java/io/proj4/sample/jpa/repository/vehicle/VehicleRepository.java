package io.proj4.sample.jpa.repository.vehicle;

import java.util.List;
import java.util.Optional;

import io.proj4.sample.jpa.model.Vehicle;
import io.proj4.sample.jpa.repository.Specification;

public interface VehicleRepository {
	Vehicle save(Vehicle vechicle);
	Optional<Vehicle> findById(long id);
	List<Vehicle> findBySpecification(Specification<Vehicle> specification);
	boolean removeById(long id);
	void remove(Vehicle vehicle);
}
