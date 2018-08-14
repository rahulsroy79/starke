package com.starke.repository;

import com.starke.domain.StarkeUserTypes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StarkeUserTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StarkeUserTypesRepository extends JpaRepository<StarkeUserTypes, Long> {

}
