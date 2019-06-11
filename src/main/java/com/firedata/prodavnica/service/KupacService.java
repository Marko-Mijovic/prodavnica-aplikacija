package com.firedata.prodavnica.service;

import com.firedata.prodavnica.domain.Kupac;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Kupac}.
 */
public interface KupacService {

    /**
     * Save a kupac.
     *
     * @param kupac the entity to save.
     * @return the persisted entity.
     */
    Kupac save(Kupac kupac);

    /**
     * Get all the kupacs.
     *
     * @return the list of entities.
     */
    List<Kupac> findAll();


    /**
     * Get the "id" kupac.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Kupac> findOne(Long id);

    /**
     * Delete the "id" kupac.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
