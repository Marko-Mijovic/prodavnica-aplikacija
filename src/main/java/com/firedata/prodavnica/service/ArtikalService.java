package com.firedata.prodavnica.service;

import com.firedata.prodavnica.domain.Artikal;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Artikal}.
 */
public interface ArtikalService {

    /**
     * Save a artikal.
     *
     * @param artikal the entity to save.
     * @return the persisted entity.
     */
    Artikal save(Artikal artikal);

    /**
     * Get all the artikals.
     *
     * @return the list of entities.
     */
    List<Artikal> findAll();


    /**
     * Get the "id" artikal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Artikal> findOne(Long id);

    /**
     * Delete the "id" artikal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
