package com.firedata.prodavnica.service.impl;

import com.firedata.prodavnica.service.AdresaService;
import com.firedata.prodavnica.domain.Adresa;
import com.firedata.prodavnica.repository.AdresaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Adresa}.
 */
@Service
@Transactional
public class AdresaServiceImpl implements AdresaService {

    private final Logger log = LoggerFactory.getLogger(AdresaServiceImpl.class);

    private final AdresaRepository adresaRepository;

    public AdresaServiceImpl(AdresaRepository adresaRepository) {
        this.adresaRepository = adresaRepository;
    }

    /**
     * Save a adresa.
     *
     * @param adresa the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Adresa save(Adresa adresa) {
        log.debug("Request to save Adresa : {}", adresa);
        return adresaRepository.save(adresa);
    }

    /**
     * Get all the adresas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Adresa> findAll() {
        log.debug("Request to get all Adresas");
        return adresaRepository.findAll();
    }


    /**
     * Get one adresa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Adresa> findOne(Long id) {
        log.debug("Request to get Adresa : {}", id);
        return adresaRepository.findById(id);
    }

    /**
     * Delete the adresa by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Adresa : {}", id);
        adresaRepository.deleteById(id);
    }
}
