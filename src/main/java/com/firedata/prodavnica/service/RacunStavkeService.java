package com.firedata.prodavnica.service;

import com.firedata.prodavnica.domain.RacunStavke;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link RacunStavke}.
 */
public interface RacunStavkeService {

    /**
     * Save a racunStavke.
     *
     * @param racunStavke the entity to save.
     * @return the persisted entity.
     */
    RacunStavke save(RacunStavke racunStavke);

    /**
     * Get all the racunStavkes.
     *
     * @return the list of entities.
     */
    List<RacunStavke> findAll();


    /**
     * Get the "id" racunStavke.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RacunStavke> findOne(Long id);

    /**
     * Delete the "id" racunStavke.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
