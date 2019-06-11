package com.firedata.prodavnica.service.impl;

import com.firedata.prodavnica.service.KupacService;
import com.firedata.prodavnica.domain.Kupac;
import com.firedata.prodavnica.repository.KupacRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Kupac}.
 */
@Service
@Transactional
public class KupacServiceImpl implements KupacService {

    private final Logger log = LoggerFactory.getLogger(KupacServiceImpl.class);

    private final KupacRepository kupacRepository;

    public KupacServiceImpl(KupacRepository kupacRepository) {
        this.kupacRepository = kupacRepository;
    }

    /**
     * Save a kupac.
     *
     * @param kupac the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Kupac save(Kupac kupac) {
        log.debug("Request to save Kupac : {}", kupac);
        return kupacRepository.save(kupac);
    }

    /**
     * Get all the kupacs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Kupac> findAll() {
        log.debug("Request to get all Kupacs");
        return kupacRepository.findAll();
    }


    /**
     * Get one kupac by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Kupac> findOne(Long id) {
        log.debug("Request to get Kupac : {}", id);
        return kupacRepository.findById(id);
    }

    /**
     * Delete the kupac by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Kupac : {}", id);
        kupacRepository.deleteById(id);
    }
}
