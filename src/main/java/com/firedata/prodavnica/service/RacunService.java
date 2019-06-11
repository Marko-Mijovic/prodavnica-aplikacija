package com.firedata.prodavnica.service;

import com.firedata.prodavnica.domain.Racun;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Racun}.
 */
public interface RacunService {

    /**
     * Save a racun.
     *
     * @param racun the entity to save.
     * @return the persisted entity.
     */
    Racun save(Racun racun);

    /**
     * Get all the racuns.
     *
     * @return the list of entities.
     */
    List<Racun> findAll();


    /**
     * Get the "id" racun.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Racun> findOne(Long id);

    /**
     * Delete the "id" racun.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
