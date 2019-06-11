package com.firedata.prodavnica.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Artikal.
 */
@Entity
@Table(name = "artikal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Artikal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv_artikla")
    private String nazivArtikla;

    @Column(name = "cena")
    private Double cena;

    @OneToMany(mappedBy = "artikal")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RacunStavke> racunStavkes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public Artikal nazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
        return this;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public Double getCena() {
        return cena;
    }

    public Artikal cena(Double cena) {
        this.cena = cena;
        return this;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Set<RacunStavke> getRacunStavkes() {
        return racunStavkes;
    }

    public Artikal racunStavkes(Set<RacunStavke> racunStavkes) {
        this.racunStavkes = racunStavkes;
        return this;
    }

    public Artikal addRacunStavke(RacunStavke racunStavke) {
        this.racunStavkes.add(racunStavke);
        racunStavke.setArtikal(this);
        return this;
    }

    public Artikal removeRacunStavke(RacunStavke racunStavke) {
        this.racunStavkes.remove(racunStavke);
        racunStavke.setArtikal(null);
        return this;
    }

    public void setRacunStavkes(Set<RacunStavke> racunStavkes) {
        this.racunStavkes = racunStavkes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Artikal)) {
            return false;
        }
        return id != null && id.equals(((Artikal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Artikal{" +
            "id=" + getId() +
            ", nazivArtikla='" + getNazivArtikla() + "'" +
            ", cena=" + getCena() +
            "}";
    }
}
