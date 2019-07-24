package io.proj4.sample.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static final EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("JpaSamplePU");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public static void closeFactory() {
		factory.close();
	}
}
