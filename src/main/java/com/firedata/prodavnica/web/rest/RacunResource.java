package com.firedata.prodavnica.web.rest;

import com.firedata.prodavnica.domain.Racun;
import com.firedata.prodavnica.service.RacunService;
import com.firedata.prodavnica.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.firedata.prodavnica.domain.Racun}.
 */
@RestController
@RequestMapping("/api")
public class RacunResource {

    private final Logger log = LoggerFactory.getLogger(RacunResource.class);

    private static final String ENTITY_NAME = "racun";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RacunService racunService;

    public RacunResource(RacunService racunService) {
        this.racunService = racunService;
    }

    /**
     * {@code POST  /racuns} : Create a new racun.
     *
     * @param racun the racun to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new racun, or with status {@code 400 (Bad Request)} if the racun has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/racuns")
    public ResponseEntity<Racun> createRacun(@RequestBody Racun racun) throws URISyntaxException {
        log.debug("REST request to save Racun : {}", racun);
        if (racun.getId() != null) {
            throw new BadRequestAlertException("A new racun cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Racun result = racunService.save(racun);
        return ResponseEntity.created(new URI("/api/racuns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /racuns} : Updates an existing racun.
     *
     * @param racun the racun to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated racun,
     * or with status {@code 400 (Bad Request)} if the racun is not valid,
     * or with status {@code 500 (Internal Server Error)} if the racun couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/racuns")
    public ResponseEntity<Racun> updateRacun(@RequestBody Racun racun) throws URISyntaxException {
        log.debug("REST request to update Racun : {}", racun);
        if (racun.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Racun result = racunService.save(racun);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, racun.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /racuns} : get all the racuns.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of racuns in body.
     */
    @GetMapping("/racuns")
    public List<Racun> getAllRacuns() {
        log.debug("REST request to get all Racuns");
        return racunService.findAll();
    }

    /**
     * {@code GET  /racuns/:id} : get the "id" racun.
     *
     * @param id the id of the racun to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the racun, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/racuns/{id}")
    public ResponseEntity<Racun> getRacun(@PathVariable Long id) {
        log.debug("REST request to get Racun : {}", id);
        Optional<Racun> racun = racunService.findOne(id);
        return ResponseUtil.wrapOrNotFound(racun);
    }

    /**
     * {@code DELETE  /racuns/:id} : delete the "id" racun.
     *
     * @param id the id of the racun to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/racuns/{id}")
    public ResponseEntity<Void> deleteRacun(@PathVariable Long id) {
        log.debug("REST request to delete Racun : {}", id);
        racunService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
