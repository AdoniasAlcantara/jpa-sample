package io.proj4.sample.jpa.repository.vehicle;

import static io.proj4.sample.jpa.model.FuelType.ETHANOL;
import static io.proj4.sample.jpa.model.FuelType.FLEXIBLE;
import static io.proj4.sample.jpa.model.FuelType.GASOLINE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import io.proj4.sample.jpa.model.FuelType;
import io.proj4.sample.jpa.model.Vehicle;
import io.proj4.sample.jpa.repository.Specification;
import io.proj4.sample.jpa.repository.Specifications;

public class VehicleSpecification implements Specification<Vehicle> {
	private VehicleCriteria criteria;
	
	public VehicleSpecification(VehicleCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Vehicle> root, CriteriaBuilder criteriaBuilder) {
		List<Specification<Vehicle>> specs = new ArrayList<>();
		
		if (criteria.getMake() != null) {
			specs.add(Specifications.like("make", criteria.getMake()));
		}
		
		if (criteria.getModel() != null) {
			specs.add(Specifications.like("model", criteria.getModel()));
		}
		
		if (criteria.getFromModelYear() != 0) {
			specs.add(Specifications.relational("modelYear", ">=", criteria.getFromModelYear()));
		}
		
		if (criteria.getMaxPrice() != null) {
			specs.add(Specifications.relational("price", "<=", criteria.getMaxPrice()));
		}
		
		if (criteria.getFuelType() != null) {
			Set<FuelType> fuelTypes = new HashSet<>();
			fuelTypes.add(criteria.getFuelType());
			
			if (criteria.getFuelType() == GASOLINE || criteria.getFuelType() == ETHANOL) {
				fuelTypes.add(FLEXIBLE);
			}
			
			specs.add(Specifications.in("fuelType", fuelTypes));
		}
		
		if (specs.size() == 0) {
			return null;
		}
		
		Specification<Vehicle> specification = specs.get(0);
		
		for (int i = 1; i < specs.size(); i++) {
			specification = Specifications.and(specification, specs.get(i));
		}
		
		return specification.toPredicate(root, criteriaBuilder);
	}
}
