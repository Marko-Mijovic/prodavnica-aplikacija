package com.firedata.prodavnica.service;

import com.firedata.prodavnica.domain.Adresa;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Adresa}.
 */
public interface AdresaService {

    /**
     * Save a adresa.
     *
     * @param adresa the entity to save.
     * @return the persisted entity.
     */
    Adresa save(Adresa adresa);

    /**
     * Get all the adresas.
     *
     * @return the list of entities.
     */
    List<Adresa> findAll();


    /**
     * Get the "id" adresa.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Adresa> findOne(Long id);

    /**
     * Delete the "id" adresa.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
