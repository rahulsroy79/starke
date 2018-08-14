package com.starke.repository;

import com.starke.domain.StarkeUsers;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StarkeUsers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StarkeUsersRepository extends JpaRepository<StarkeUsers, Long> {

}
