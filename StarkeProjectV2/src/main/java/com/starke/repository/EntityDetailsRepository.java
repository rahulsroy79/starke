package com.starke.repository;

import com.starke.domain.EntityDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EntityDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityDetailsRepository extends JpaRepository<EntityDetails, Long> {

}
