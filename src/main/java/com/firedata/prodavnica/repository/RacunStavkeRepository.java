package com.firedata.prodavnica.repository;

import com.firedata.prodavnica.domain.RacunStavke;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RacunStavke entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RacunStavkeRepository extends JpaRepository<RacunStavke, Long> {

}
