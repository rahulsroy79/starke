package com.starke.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.starke.domain.StarkeEntity;

public class StarkeEntityCustomRepositoryImpl implements StarkeEntityCustomRepository{

	@PersistenceContext
    EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StarkeEntity> findByParententityid(Long id) {
		javax.persistence.Query query = entityManager.createNativeQuery(
				"SELECT * from Starke_Entity where id = ?", StarkeEntity.class);
		
		query.setParameter(1, id);
		
		return query.getResultList();
	}

}
