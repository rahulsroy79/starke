package com.starke.repository;

import com.starke.domain.StarkeEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StarkeEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StarkeEntityRepository extends JpaRepository<StarkeEntity, Long> {

}
