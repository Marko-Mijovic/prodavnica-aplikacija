package com.firedata.prodavnica.service.impl;

import com.firedata.prodavnica.service.RacunService;
import com.firedata.prodavnica.domain.Racun;
import com.firedata.prodavnica.repository.RacunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Racun}.
 */
@Service
@Transactional
public class RacunServiceImpl implements RacunService {

    private final Logger log = LoggerFactory.getLogger(RacunServiceImpl.class);

    private final RacunRepository racunRepository;

    public RacunServiceImpl(RacunRepository racunRepository) {
        this.racunRepository = racunRepository;
    }

    /**
     * Save a racun.
     *
     * @param racun the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Racun save(Racun racun) {
        log.debug("Request to save Racun : {}", racun);
        return racunRepository.save(racun);
    }

    /**
     * Get all the racuns.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Racun> findAll() {
        log.debug("Request to get all Racuns");
        return racunRepository.findAll();
    }


    /**
     * Get one racun by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Racun> findOne(Long id) {
        log.debug("Request to get Racun : {}", id);
        return racunRepository.findById(id);
    }

    /**
     * Delete the racun by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Racun : {}", id);
        racunRepository.deleteById(id);
    }
}
