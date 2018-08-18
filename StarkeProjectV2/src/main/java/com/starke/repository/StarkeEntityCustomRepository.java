package com.starke.repository;

import java.util.List;

import com.starke.domain.StarkeEntity;

public interface StarkeEntityCustomRepository{
	// @Query("SELECT starkeentity from StarkeEntity starkeentity where starkeentity.id = :id")
	List<StarkeEntity> findByParententityid(Long id);
}
