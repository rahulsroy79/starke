package com.starke.repository;

import com.starke.domain.EntityPageDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EntityPageDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityPageDetailsRepository extends JpaRepository<EntityPageDetails, Long> {

}
