package io.proj4.sample.jpa.repository.owner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import io.proj4.sample.jpa.model.Owner;

public class OwnerRepositoryImpl implements OwnerRepository {
	private EntityManager entityManager;

	public OwnerRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Owner save(Owner owner) {
		if (owner.getId() != null) {
			return entityManager.merge(owner);
		}
		
		entityManager.persist(owner);
		return owner;
	}

	@Override
	public Optional<Owner> findById(long id) { 
		return Optional.ofNullable(entityManager.find(Owner.class, id));
	}

	@Override
	public List<Owner> findByName(String name) {
		String jpql = "from Owner where upper(name) like concat('%', :name, '%')";
		return entityManager.createQuery(jpql, Owner.class)
				.setParameter("name", name.toUpperCase())
				.getResultList();
	}

	@Override
	public List<Owner> findBornBefore(LocalDate date) {
		return findBorn("<=", date);
	}
	
	@Override
	public List<Owner> findBornAfter(LocalDate date) {
		return findBorn(">=", date);
	}

	@Override
	public List<Owner> findAll() {
		return entityManager.createQuery("from Owner", Owner.class).getResultList();
	}

	@Override
	public boolean removeById(long id) {
		String jpql = "delete from Owner where id = :id";
		int result = entityManager.createQuery(jpql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result > 0;
	}

	@Override
	public void remove(Owner owner) {
		entityManager.remove(owner);
	}
	
	private List<Owner> findBorn(String operator, LocalDate date) {
		String jpql = String.format("from Owner where birth %s :date", operator);
		return entityManager.createQuery(jpql, Owner.class)
				.setParameter("date", date)
				.getResultList();
	}
}
