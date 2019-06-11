package com.firedata.prodavnica.web.rest;

import com.firedata.prodavnica.ProdavnicaApp;
import com.firedata.prodavnica.domain.Adresa;
import com.firedata.prodavnica.repository.AdresaRepository;
import com.firedata.prodavnica.service.AdresaService;
import com.firedata.prodavnica.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.firedata.prodavnica.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link AdresaResource} REST controller.
 */
@SpringBootTest(classes = ProdavnicaApp.class)
public class AdresaResourceIT {

    private static final String DEFAULT_NAZIV_ULICE = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV_ULICE = "BBBBBBBBBB";

    private static final Long DEFAULT_PTT = 1L;
    private static final Long UPDATED_PTT = 2L;

    @Autowired
    private AdresaRepository adresaRepository;

    @Autowired
    private AdresaService adresaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restAdresaMockMvc;

    private Adresa adresa;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdresaResource adresaResource = new AdresaResource(adresaService);
        this.restAdresaMockMvc = MockMvcBuilders.standaloneSetup(adresaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Adresa createEntity(EntityManager em) {
        Adresa adresa = new Adresa()
            .nazivUlice(DEFAULT_NAZIV_ULICE)
            .ptt(DEFAULT_PTT);
        return adresa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Adresa createUpdatedEntity(EntityManager em) {
        Adresa adresa = new Adresa()
            .nazivUlice(UPDATED_NAZIV_ULICE)
            .ptt(UPDATED_PTT);
        return adresa;
    }

    @BeforeEach
    public void initTest() {
        adresa = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdresa() throws Exception {
        int databaseSizeBeforeCreate = adresaRepository.findAll().size();

        // Create the Adresa
        restAdresaMockMvc.perform(post("/api/adresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adresa)))
            .andExpect(status().isCreated());

        // Validate the Adresa in the database
        List<Adresa> adresaList = adresaRepository.findAll();
        assertThat(adresaList).hasSize(databaseSizeBeforeCreate + 1);
        Adresa testAdresa = adresaList.get(adresaList.size() - 1);
        assertThat(testAdresa.getNazivUlice()).isEqualTo(DEFAULT_NAZIV_ULICE);
        assertThat(testAdresa.getPtt()).isEqualTo(DEFAULT_PTT);
    }

    @Test
    @Transactional
    public void createAdresaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adresaRepository.findAll().size();

        // Create the Adresa with an existing ID
        adresa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdresaMockMvc.perform(post("/api/adresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adresa)))
            .andExpect(status().isBadRequest());

        // Validate the Adresa in the database
        List<Adresa> adresaList = adresaRepository.findAll();
        assertThat(adresaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdresas() throws Exception {
        // Initialize the database
        adresaRepository.saveAndFlush(adresa);

        // Get all the adresaList
        restAdresaMockMvc.perform(get("/api/adresas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adresa.getId().intValue())))
            .andExpect(jsonPath("$.[*].nazivUlice").value(hasItem(DEFAULT_NAZIV_ULICE.toString())))
            .andExpect(jsonPath("$.[*].ptt").value(hasItem(DEFAULT_PTT.intValue())));
    }
    
    @Test
    @Transactional
    public void getAdresa() throws Exception {
        // Initialize the database
        adresaRepository.saveAndFlush(adresa);

        // Get the adresa
        restAdresaMockMvc.perform(get("/api/adresas/{id}", adresa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adresa.getId().intValue()))
            .andExpect(jsonPath("$.nazivUlice").value(DEFAULT_NAZIV_ULICE.toString()))
            .andExpect(jsonPath("$.ptt").value(DEFAULT_PTT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAdresa() throws Exception {
        // Get the adresa
        restAdresaMockMvc.perform(get("/api/adresas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdresa() throws Exception {
        // Initialize the database
        adresaService.save(adresa);

        int databaseSizeBeforeUpdate = adresaRepository.findAll().size();

        // Update the adresa
        Adresa updatedAdresa = adresaRepository.findById(adresa.getId()).get();
        // Disconnect from session so that the updates on updatedAdresa are not directly saved in db
        em.detach(updatedAdresa);
        updatedAdresa
            .nazivUlice(UPDATED_NAZIV_ULICE)
            .ptt(UPDATED_PTT);

        restAdresaMockMvc.perform(put("/api/adresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdresa)))
            .andExpect(status().isOk());

        // Validate the Adresa in the database
        List<Adresa> adresaList = adresaRepository.findAll();
        assertThat(adresaList).hasSize(databaseSizeBeforeUpdate);
        Adresa testAdresa = adresaList.get(adresaList.size() - 1);
        assertThat(testAdresa.getNazivUlice()).isEqualTo(UPDATED_NAZIV_ULICE);
        assertThat(testAdresa.getPtt()).isEqualTo(UPDATED_PTT);
    }

    @Test
    @Transactional
    public void updateNonExistingAdresa() throws Exception {
        int databaseSizeBeforeUpdate = adresaRepository.findAll().size();

        // Create the Adresa

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdresaMockMvc.perform(put("/api/adresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adresa)))
            .andExpect(status().isBadRequest());

        // Validate the Adresa in the database
        List<Adresa> adresaList = adresaRepository.findAll();
        assertThat(adresaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdresa() throws Exception {
        // Initialize the database
        adresaService.save(adresa);

        int databaseSizeBeforeDelete = adresaRepository.findAll().size();

        // Delete the adresa
        restAdresaMockMvc.perform(delete("/api/adresas/{id}", adresa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Adresa> adresaList = adresaRepository.findAll();
        assertThat(adresaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Adresa.class);
        Adresa adresa1 = new Adresa();
        adresa1.setId(1L);
        Adresa adresa2 = new Adresa();
        adresa2.setId(adresa1.getId());
        assertThat(adresa1).isEqualTo(adresa2);
        adresa2.setId(2L);
        assertThat(adresa1).isNotEqualTo(adresa2);
        adresa1.setId(null);
        assertThat(adresa1).isNotEqualTo(adresa2);
    }
}
