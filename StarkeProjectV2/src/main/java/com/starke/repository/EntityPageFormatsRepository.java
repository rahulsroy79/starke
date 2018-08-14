package com.starke.repository;

import com.starke.domain.EntityPageFormats;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EntityPageFormats entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityPageFormatsRepository extends JpaRepository<EntityPageFormats, Long> {

}
