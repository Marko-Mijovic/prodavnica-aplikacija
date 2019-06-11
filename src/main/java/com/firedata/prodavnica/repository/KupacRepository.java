package com.firedata.prodavnica.repository;

import com.firedata.prodavnica.domain.Kupac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Kupac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KupacRepository extends JpaRepository<Kupac, Long> {

}
