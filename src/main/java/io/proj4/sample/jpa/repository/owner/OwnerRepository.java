package io.proj4.sample.jpa.repository.owner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import io.proj4.sample.jpa.model.Owner;

public interface OwnerRepository {
	Owner save(Owner owner);
	Optional<Owner> findById(long id);
	List<Owner> findByName(String name);
	List<Owner> findBornBefore(LocalDate date);
	List<Owner> findBornAfter(LocalDate date);
	List<Owner> findAll();
	boolean removeById(long id);
	void remove(Owner owner);
}
