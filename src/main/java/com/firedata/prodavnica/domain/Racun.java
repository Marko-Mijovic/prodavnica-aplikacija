package com.firedata.prodavnica.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Racun.
 */
@Entity
@Table(name = "racun")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Racun implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "broj_racuna")
    private String brojRacuna;

    @ManyToOne
    @JsonIgnoreProperties("racuns")
    private Kupac kupac;

    @OneToMany(mappedBy = "racun")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RacunStavke> racunStavkes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrojRacuna() {
        return brojRacuna;
    }

    public Racun brojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
        return this;
    }

    public void setBrojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public Racun kupac(Kupac kupac) {
        this.kupac = kupac;
        return this;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Set<RacunStavke> getRacunStavkes() {
        return racunStavkes;
    }

    public Racun racunStavkes(Set<RacunStavke> racunStavkes) {
        this.racunStavkes = racunStavkes;
        return this;
    }

    public Racun addRacunStavke(RacunStavke racunStavke) {
        this.racunStavkes.add(racunStavke);
        racunStavke.setRacun(this);
        return this;
    }

    public Racun removeRacunStavke(RacunStavke racunStavke) {
        this.racunStavkes.remove(racunStavke);
        racunStavke.setRacun(null);
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
        if (!(o instanceof Racun)) {
            return false;
        }
        return id != null && id.equals(((Racun) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Racun{" +
            "id=" + getId() +
            ", brojRacuna='" + getBrojRacuna() + "'" +
            "}";
    }
}
