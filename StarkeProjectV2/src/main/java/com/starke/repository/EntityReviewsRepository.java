package com.starke.repository;

import com.starke.domain.EntityReviews;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EntityReviews entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityReviewsRepository extends JpaRepository<EntityReviews, Long> {

}
