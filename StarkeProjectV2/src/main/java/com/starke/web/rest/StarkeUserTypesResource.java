package com.starke.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.starke.service.StarkeUserTypesService;
import com.starke.web.rest.errors.BadRequestAlertException;
import com.starke.web.rest.util.HeaderUtil;
import com.starke.service.dto.StarkeUserTypesDTO;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing StarkeUserTypes.
 */
@RestController
@RequestMapping("/api")
public class StarkeUserTypesResource {

    private final Logger log = LoggerFactory.getLogger(StarkeUserTypesResource.class);

    private static final String ENTITY_NAME = "starkeUserTypes";

    private final StarkeUserTypesService starkeUserTypesService;

    public StarkeUserTypesResource(StarkeUserTypesService starkeUserTypesService) {
        this.starkeUserTypesService = starkeUserTypesService;
    }

    /**
     * POST  /starke-user-types : Create a new starkeUserTypes.
     *
     * @param starkeUserTypesDTO the starkeUserTypesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new starkeUserTypesDTO, or with status 400 (Bad Request) if the starkeUserTypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/starke-user-types")
    @Timed
    public ResponseEntity<StarkeUserTypesDTO> createStarkeUserTypes(@Valid @RequestBody StarkeUserTypesDTO starkeUserTypesDTO) throws URISyntaxException {
        log.debug("REST request to save StarkeUserTypes : {}", starkeUserTypesDTO);
        if (starkeUserTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new starkeUserTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StarkeUserTypesDTO result = starkeUserTypesService.save(starkeUserTypesDTO);
        return ResponseEntity.created(new URI("/api/starke-user-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /starke-user-types : Updates an existing starkeUserTypes.
     *
     * @param starkeUserTypesDTO the starkeUserTypesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated starkeUserTypesDTO,
     * or with status 400 (Bad Request) if the starkeUserTypesDTO is not valid,
     * or with status 500 (Internal Server Error) if the starkeUserTypesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/starke-user-types")
    @Timed
    public ResponseEntity<StarkeUserTypesDTO> updateStarkeUserTypes(@Valid @RequestBody StarkeUserTypesDTO starkeUserTypesDTO) throws URISyntaxException {
        log.debug("REST request to update StarkeUserTypes : {}", starkeUserTypesDTO);
        if (starkeUserTypesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StarkeUserTypesDTO result = starkeUserTypesService.save(starkeUserTypesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, starkeUserTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /starke-user-types : get all the starkeUserTypes.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of starkeUserTypes in body
     */
    @GetMapping("/starke-user-types")
    @Timed
    public List<StarkeUserTypesDTO> getAllStarkeUserTypes(@RequestParam(required = false) String filter) {
        if ("starkeuser-is-null".equals(filter)) {
            log.debug("REST request to get all StarkeUserTypess where starkeUser is null");
            return starkeUserTypesService.findAllWhereStarkeUserIsNull();
        }
        log.debug("REST request to get all StarkeUserTypes");
        return starkeUserTypesService.findAll();
    }

    /**
     * GET  /starke-user-types/:id : get the "id" starkeUserTypes.
     *
     * @param id the id of the starkeUserTypesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the starkeUserTypesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/starke-user-types/{id}")
    @Timed
    public ResponseEntity<StarkeUserTypesDTO> getStarkeUserTypes(@PathVariable Long id) {
        log.debug("REST request to get StarkeUserTypes : {}", id);
        Optional<StarkeUserTypesDTO> starkeUserTypesDTO = starkeUserTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(starkeUserTypesDTO);
    }

    /**
     * DELETE  /starke-user-types/:id : delete the "id" starkeUserTypes.
     *
     * @param id the id of the starkeUserTypesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/starke-user-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteStarkeUserTypes(@PathVariable Long id) {
        log.debug("REST request to delete StarkeUserTypes : {}", id);
        starkeUserTypesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
