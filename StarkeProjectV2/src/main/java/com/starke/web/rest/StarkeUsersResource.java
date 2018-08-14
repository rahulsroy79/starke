package com.starke.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.starke.service.StarkeUsersService;
import com.starke.web.rest.errors.BadRequestAlertException;
import com.starke.web.rest.util.HeaderUtil;
import com.starke.service.dto.StarkeUsersDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing StarkeUsers.
 */
@RestController
@RequestMapping("/api")
public class StarkeUsersResource {

    private final Logger log = LoggerFactory.getLogger(StarkeUsersResource.class);

    private static final String ENTITY_NAME = "starkeUsers";

    private final StarkeUsersService starkeUsersService;

    public StarkeUsersResource(StarkeUsersService starkeUsersService) {
        this.starkeUsersService = starkeUsersService;
    }

    /**
     * POST  /starke-users : Create a new starkeUsers.
     *
     * @param starkeUsersDTO the starkeUsersDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new starkeUsersDTO, or with status 400 (Bad Request) if the starkeUsers has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/starke-users")
    @Timed
    public ResponseEntity<StarkeUsersDTO> createStarkeUsers(@Valid @RequestBody StarkeUsersDTO starkeUsersDTO) throws URISyntaxException {
        log.debug("REST request to save StarkeUsers : {}", starkeUsersDTO);
        if (starkeUsersDTO.getId() != null) {
            throw new BadRequestAlertException("A new starkeUsers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StarkeUsersDTO result = starkeUsersService.save(starkeUsersDTO);
        return ResponseEntity.created(new URI("/api/starke-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /starke-users : Updates an existing starkeUsers.
     *
     * @param starkeUsersDTO the starkeUsersDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated starkeUsersDTO,
     * or with status 400 (Bad Request) if the starkeUsersDTO is not valid,
     * or with status 500 (Internal Server Error) if the starkeUsersDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/starke-users")
    @Timed
    public ResponseEntity<StarkeUsersDTO> updateStarkeUsers(@Valid @RequestBody StarkeUsersDTO starkeUsersDTO) throws URISyntaxException {
        log.debug("REST request to update StarkeUsers : {}", starkeUsersDTO);
        if (starkeUsersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StarkeUsersDTO result = starkeUsersService.save(starkeUsersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, starkeUsersDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /starke-users : get all the starkeUsers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of starkeUsers in body
     */
    @GetMapping("/starke-users")
    @Timed
    public List<StarkeUsersDTO> getAllStarkeUsers() {
        log.debug("REST request to get all StarkeUsers");
        return starkeUsersService.findAll();
    }

    /**
     * GET  /starke-users/:id : get the "id" starkeUsers.
     *
     * @param id the id of the starkeUsersDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the starkeUsersDTO, or with status 404 (Not Found)
     */
    @GetMapping("/starke-users/{id}")
    @Timed
    public ResponseEntity<StarkeUsersDTO> getStarkeUsers(@PathVariable Long id) {
        log.debug("REST request to get StarkeUsers : {}", id);
        Optional<StarkeUsersDTO> starkeUsersDTO = starkeUsersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(starkeUsersDTO);
    }

    /**
     * DELETE  /starke-users/:id : delete the "id" starkeUsers.
     *
     * @param id the id of the starkeUsersDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/starke-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteStarkeUsers(@PathVariable Long id) {
        log.debug("REST request to delete StarkeUsers : {}", id);
        starkeUsersService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
