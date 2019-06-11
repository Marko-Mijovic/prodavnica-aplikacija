package com.firedata.prodavnica.web.rest;

import com.firedata.prodavnica.domain.Adresa;
import com.firedata.prodavnica.service.AdresaService;
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
 * REST controller for managing {@link com.firedata.prodavnica.domain.Adresa}.
 */
@RestController
@RequestMapping("/api")
public class AdresaResource {

    private final Logger log = LoggerFactory.getLogger(AdresaResource.class);

    private static final String ENTITY_NAME = "adresa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdresaService adresaService;

    public AdresaResource(AdresaService adresaService) {
        this.adresaService = adresaService;
    }

    /**
     * {@code POST  /adresas} : Create a new adresa.
     *
     * @param adresa the adresa to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adresa, or with status {@code 400 (Bad Request)} if the adresa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adresas")
    public ResponseEntity<Adresa> createAdresa(@RequestBody Adresa adresa) throws URISyntaxException {
        log.debug("REST request to save Adresa : {}", adresa);
        if (adresa.getId() != null) {
            throw new BadRequestAlertException("A new adresa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Adresa result = adresaService.save(adresa);
        return ResponseEntity.created(new URI("/api/adresas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adresas} : Updates an existing adresa.
     *
     * @param adresa the adresa to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adresa,
     * or with status {@code 400 (Bad Request)} if the adresa is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adresa couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adresas")
    public ResponseEntity<Adresa> updateAdresa(@RequestBody Adresa adresa) throws URISyntaxException {
        log.debug("REST request to update Adresa : {}", adresa);
        if (adresa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Adresa result = adresaService.save(adresa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, adresa.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adresas} : get all the adresas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adresas in body.
     */
    @GetMapping("/adresas")
    public List<Adresa> getAllAdresas() {
        log.debug("REST request to get all Adresas");
        return adresaService.findAll();
    }

    /**
     * {@code GET  /adresas/:id} : get the "id" adresa.
     *
     * @param id the id of the adresa to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adresa, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adresas/{id}")
    public ResponseEntity<Adresa> getAdresa(@PathVariable Long id) {
        log.debug("REST request to get Adresa : {}", id);
        Optional<Adresa> adresa = adresaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adresa);
    }

    /**
     * {@code DELETE  /adresas/:id} : delete the "id" adresa.
     *
     * @param id the id of the adresa to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adresas/{id}")
    public ResponseEntity<Void> deleteAdresa(@PathVariable Long id) {
        log.debug("REST request to delete Adresa : {}", id);
        adresaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
