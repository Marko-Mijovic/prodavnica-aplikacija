package com.firedata.prodavnica.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Adresa.
 */
@Entity
@Table(name = "adresa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Adresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv_ulice")
    private String nazivUlice;

    @Column(name = "ptt")
    private Long ptt;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivUlice() {
        return nazivUlice;
    }

    public Adresa nazivUlice(String nazivUlice) {
        this.nazivUlice = nazivUlice;
        return this;
    }

    public void setNazivUlice(String nazivUlice) {
        this.nazivUlice = nazivUlice;
    }

    public Long getPtt() {
        return ptt;
    }

    public Adresa ptt(Long ptt) {
        this.ptt = ptt;
        return this;
    }

    public void setPtt(Long ptt) {
        this.ptt = ptt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Adresa)) {
            return false;
        }
        return id != null && id.equals(((Adresa) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Adresa{" +
            "id=" + getId() +
            ", nazivUlice='" + getNazivUlice() + "'" +
            ", ptt=" + getPtt() +
            "}";
    }
}
