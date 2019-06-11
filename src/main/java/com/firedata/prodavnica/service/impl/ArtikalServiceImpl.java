package com.firedata.prodavnica.service.impl;

import com.firedata.prodavnica.service.ArtikalService;
import com.firedata.prodavnica.domain.Artikal;
import com.firedata.prodavnica.repository.ArtikalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Artikal}.
 */
@Service
@Transactional
public class ArtikalServiceImpl implements ArtikalService {

    private final Logger log = LoggerFactory.getLogger(ArtikalServiceImpl.class);

    private final ArtikalRepository artikalRepository;

    public ArtikalServiceImpl(ArtikalRepository artikalRepository) {
        this.artikalRepository = artikalRepository;
    }

    /**
     * Save a artikal.
     *
     * @param artikal the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Artikal save(Artikal artikal) {
        log.debug("Request to save Artikal : {}", artikal);
        return artikalRepository.save(artikal);
    }

    /**
     * Get all the artikals.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Artikal> findAll() {
        log.debug("Request to get all Artikals");
        return artikalRepository.findAll();
    }


    /**
     * Get one artikal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Artikal> findOne(Long id) {
        log.debug("Request to get Artikal : {}", id);
        return artikalRepository.findById(id);
    }

    /**
     * Delete the artikal by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Artikal : {}", id);
        artikalRepository.deleteById(id);
    }
}
