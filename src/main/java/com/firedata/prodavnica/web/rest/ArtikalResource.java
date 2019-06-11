package com.firedata.prodavnica.web.rest;

import com.firedata.prodavnica.domain.Artikal;
import com.firedata.prodavnica.service.ArtikalService;
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
 * REST controller for managing {@link com.firedata.prodavnica.domain.Artikal}.
 */
@RestController
@RequestMapping("/api")
public class ArtikalResource {

    private final Logger log = LoggerFactory.getLogger(ArtikalResource.class);

    private static final String ENTITY_NAME = "artikal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArtikalService artikalService;

    public ArtikalResource(ArtikalService artikalService) {
        this.artikalService = artikalService;
    }

    /**
     * {@code POST  /artikals} : Create a new artikal.
     *
     * @param artikal the artikal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new artikal, or with status {@code 400 (Bad Request)} if the artikal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/artikals")
    public ResponseEntity<Artikal> createArtikal(@RequestBody Artikal artikal) throws URISyntaxException {
        log.debug("REST request to save Artikal : {}", artikal);
        if (artikal.getId() != null) {
            throw new BadRequestAlertException("A new artikal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Artikal result = artikalService.save(artikal);
        return ResponseEntity.created(new URI("/api/artikals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /artikals} : Updates an existing artikal.
     *
     * @param artikal the artikal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated artikal,
     * or with status {@code 400 (Bad Request)} if the artikal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the artikal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/artikals")
    public ResponseEntity<Artikal> updateArtikal(@RequestBody Artikal artikal) throws URISyntaxException {
        log.debug("REST request to update Artikal : {}", artikal);
        if (artikal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Artikal result = artikalService.save(artikal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, artikal.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /artikals} : get all the artikals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of artikals in body.
     */
    @GetMapping("/artikals")
    public List<Artikal> getAllArtikals() {
        log.debug("REST request to get all Artikals");
        return artikalService.findAll();
    }

    /**
     * {@code GET  /artikals/:id} : get the "id" artikal.
     *
     * @param id the id of the artikal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the artikal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/artikals/{id}")
    public ResponseEntity<Artikal> getArtikal(@PathVariable Long id) {
        log.debug("REST request to get Artikal : {}", id);
        Optional<Artikal> artikal = artikalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(artikal);
    }

    /**
     * {@code DELETE  /artikals/:id} : delete the "id" artikal.
     *
     * @param id the id of the artikal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/artikals/{id}")
    public ResponseEntity<Void> deleteArtikal(@PathVariable Long id) {
        log.debug("REST request to delete Artikal : {}", id);
        artikalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
