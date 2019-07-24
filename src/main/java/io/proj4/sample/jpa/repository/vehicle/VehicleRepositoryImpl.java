package io.proj4.sample.jpa.repository.vehicle;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.proj4.sample.jpa.model.Vehicle;
import io.proj4.sample.jpa.repository.Specification;

public class VehicleRepositoryImpl implements VehicleRepository {
	private EntityManager entityManager;
	
	public VehicleRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Vehicle save(Vehicle vehicle) {
		if (vehicle.getId() != null) {
			return entityManager.merge(vehicle);
		}
		
		entityManager.persist(vehicle);
		return vehicle;
	}
	
	@Override
	public Optional<Vehicle> findById(long id) {
		return Optional.ofNullable(entityManager.find(Vehicle.class, id));
	}
	
	@Override
	public List<Vehicle> findBySpecification(Specification<Vehicle> specification) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteriaQuery = builder.createQuery(Vehicle.class);
		Root<Vehicle> root = criteriaQuery.from(Vehicle.class);
		root.fetch("owner");
		criteriaQuery.where(specification.toPredicate(root, builder));
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	@Override
	public boolean removeById(long id) {
		int result = entityManager.createQuery("delete from Vehicle where id = :id")
				.setParameter("id", id)
				.executeUpdate();
				
		return result > 0;
	}
	
	@Override
	public void remove(Vehicle vehicle) {
		entityManager.remove(vehicle);
	}
}
