package com.firedata.prodavnica.service.impl;

import com.firedata.prodavnica.service.RacunStavkeService;
import com.firedata.prodavnica.domain.RacunStavke;
import com.firedata.prodavnica.repository.RacunStavkeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link RacunStavke}.
 */
@Service
@Transactional
public class RacunStavkeServiceImpl implements RacunStavkeService {

    private final Logger log = LoggerFactory.getLogger(RacunStavkeServiceImpl.class);

    private final RacunStavkeRepository racunStavkeRepository;

    public RacunStavkeServiceImpl(RacunStavkeRepository racunStavkeRepository) {
        this.racunStavkeRepository = racunStavkeRepository;
    }

    /**
     * Save a racunStavke.
     *
     * @param racunStavke the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RacunStavke save(RacunStavke racunStavke) {
        log.debug("Request to save RacunStavke : {}", racunStavke);
        return racunStavkeRepository.save(racunStavke);
    }

    /**
     * Get all the racunStavkes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RacunStavke> findAll() {
        log.debug("Request to get all RacunStavkes");
        return racunStavkeRepository.findAll();
    }


    /**
     * Get one racunStavke by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RacunStavke> findOne(Long id) {
        log.debug("Request to get RacunStavke : {}", id);
        return racunStavkeRepository.findById(id);
    }

    /**
     * Delete the racunStavke by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RacunStavke : {}", id);
        racunStavkeRepository.deleteById(id);
    }
}
