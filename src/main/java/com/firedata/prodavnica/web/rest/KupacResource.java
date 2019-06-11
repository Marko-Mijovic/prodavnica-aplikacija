package com.firedata.prodavnica.web.rest;

import com.firedata.prodavnica.domain.Kupac;
import com.firedata.prodavnica.service.KupacService;
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
 * REST controller for managing {@link com.firedata.prodavnica.domain.Kupac}.
 */
@RestController
@RequestMapping("/api")
public class KupacResource {

    private final Logger log = LoggerFactory.getLogger(KupacResource.class);

    private static final String ENTITY_NAME = "kupac";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KupacService kupacService;

    public KupacResource(KupacService kupacService) {
        this.kupacService = kupacService;
    }

    /**
     * {@code POST  /kupacs} : Create a new kupac.
     *
     * @param kupac the kupac to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kupac, or with status {@code 400 (Bad Request)} if the kupac has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kupacs")
    public ResponseEntity<Kupac> createKupac(@RequestBody Kupac kupac) throws URISyntaxException {
        log.debug("REST request to save Kupac : {}", kupac);
        if (kupac.getId() != null) {
            throw new BadRequestAlertException("A new kupac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Kupac result = kupacService.save(kupac);
        return ResponseEntity.created(new URI("/api/kupacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kupacs} : Updates an existing kupac.
     *
     * @param kupac the kupac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kupac,
     * or with status {@code 400 (Bad Request)} if the kupac is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kupac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kupacs")
    public ResponseEntity<Kupac> updateKupac(@RequestBody Kupac kupac) throws URISyntaxException {
        log.debug("REST request to update Kupac : {}", kupac);
        if (kupac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Kupac result = kupacService.save(kupac);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, kupac.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kupacs} : get all the kupacs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kupacs in body.
     */
    @GetMapping("/kupacs")
    public List<Kupac> getAllKupacs() {
        log.debug("REST request to get all Kupacs");
        return kupacService.findAll();
    }

    /**
     * {@code GET  /kupacs/:id} : get the "id" kupac.
     *
     * @param id the id of the kupac to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kupac, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kupacs/{id}")
    public ResponseEntity<Kupac> getKupac(@PathVariable Long id) {
        log.debug("REST request to get Kupac : {}", id);
        Optional<Kupac> kupac = kupacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kupac);
    }

    /**
     * {@code DELETE  /kupacs/:id} : delete the "id" kupac.
     *
     * @param id the id of the kupac to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kupacs/{id}")
    public ResponseEntity<Void> deleteKupac(@PathVariable Long id) {
        log.debug("REST request to delete Kupac : {}", id);
        kupacService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
