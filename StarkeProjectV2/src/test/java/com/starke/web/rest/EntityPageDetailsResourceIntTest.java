package com.starke.web.rest;

import com.starke.Starkev2App;

import com.starke.domain.EntityPageDetails;
import com.starke.repository.EntityPageDetailsRepository;
import com.starke.service.EntityPageDetailsService;
import com.starke.service.dto.EntityPageDetailsDTO;
import com.starke.service.mapper.EntityPageDetailsMapper;
import com.starke.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.starke.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EntityPageDetailsResource REST controller.
 *
 * @see EntityPageDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starkev2App.class)
public class EntityPageDetailsResourceIntTest {

    private static final Integer DEFAULT_PAGEDETAILID = 1;
    private static final Integer UPDATED_PAGEDETAILID = 2;

    private static final String DEFAULT_PAGETYPENAME = "AAAAAAAAAA";
    private static final String UPDATED_PAGETYPENAME = "BBBBBBBBBB";

    @Autowired
    private EntityPageDetailsRepository entityPageDetailsRepository;


    @Autowired
    private EntityPageDetailsMapper entityPageDetailsMapper;
    

    @Autowired
    private EntityPageDetailsService entityPageDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEntityPageDetailsMockMvc;

    private EntityPageDetails entityPageDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntityPageDetailsResource entityPageDetailsResource = new EntityPageDetailsResource(entityPageDetailsService);
        this.restEntityPageDetailsMockMvc = MockMvcBuilders.standaloneSetup(entityPageDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityPageDetails createEntity(EntityManager em) {
        EntityPageDetails entityPageDetails = new EntityPageDetails()
            .pagedetailid(DEFAULT_PAGEDETAILID)
            .pagetypename(DEFAULT_PAGETYPENAME);
        return entityPageDetails;
    }

    @Before
    public void initTest() {
        entityPageDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityPageDetails() throws Exception {
        int databaseSizeBeforeCreate = entityPageDetailsRepository.findAll().size();

        // Create the EntityPageDetails
        EntityPageDetailsDTO entityPageDetailsDTO = entityPageDetailsMapper.toDto(entityPageDetails);
        restEntityPageDetailsMockMvc.perform(post("/api/entity-page-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityPageDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the EntityPageDetails in the database
        List<EntityPageDetails> entityPageDetailsList = entityPageDetailsRepository.findAll();
        assertThat(entityPageDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        EntityPageDetails testEntityPageDetails = entityPageDetailsList.get(entityPageDetailsList.size() - 1);
        assertThat(testEntityPageDetails.getPagedetailid()).isEqualTo(DEFAULT_PAGEDETAILID);
        assertThat(testEntityPageDetails.getPagetypename()).isEqualTo(DEFAULT_PAGETYPENAME);
    }

    @Test
    @Transactional
    public void createEntityPageDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityPageDetailsRepository.findAll().size();

        // Create the EntityPageDetails with an existing ID
        entityPageDetails.setId(1L);
        EntityPageDetailsDTO entityPageDetailsDTO = entityPageDetailsMapper.toDto(entityPageDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityPageDetailsMockMvc.perform(post("/api/entity-page-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityPageDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityPageDetails in the database
        List<EntityPageDetails> entityPageDetailsList = entityPageDetailsRepository.findAll();
        assertThat(entityPageDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPagedetailidIsRequired() throws Exception {
        int databaseSizeBeforeTest = entityPageDetailsRepository.findAll().size();
        // set the field null
        entityPageDetails.setPagedetailid(null);

        // Create the EntityPageDetails, which fails.
        EntityPageDetailsDTO entityPageDetailsDTO = entityPageDetailsMapper.toDto(entityPageDetails);

        restEntityPageDetailsMockMvc.perform(post("/api/entity-page-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityPageDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<EntityPageDetails> entityPageDetailsList = entityPageDetailsRepository.findAll();
        assertThat(entityPageDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPagetypenameIsRequired() throws Exception {
        int databaseSizeBeforeTest = entityPageDetailsRepository.findAll().size();
        // set the field null
        entityPageDetails.setPagetypename(null);

        // Create the EntityPageDetails, which fails.
        EntityPageDetailsDTO entityPageDetailsDTO = entityPageDetailsMapper.toDto(entityPageDetails);

        restEntityPageDetailsMockMvc.perform(post("/api/entity-page-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityPageDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<EntityPageDetails> entityPageDetailsList = entityPageDetailsRepository.findAll();
        assertThat(entityPageDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEntityPageDetails() throws Exception {
        // Initialize the database
        entityPageDetailsRepository.saveAndFlush(entityPageDetails);

        // Get all the entityPageDetailsList
        restEntityPageDetailsMockMvc.perform(get("/api/entity-page-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityPageDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].pagedetailid").value(hasItem(DEFAULT_PAGEDETAILID)))
            .andExpect(jsonPath("$.[*].pagetypename").value(hasItem(DEFAULT_PAGETYPENAME.toString())));
    }
    

    @Test
    @Transactional
    public void getEntityPageDetails() throws Exception {
        // Initialize the database
        entityPageDetailsRepository.saveAndFlush(entityPageDetails);

        // Get the entityPageDetails
        restEntityPageDetailsMockMvc.perform(get("/api/entity-page-details/{id}", entityPageDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityPageDetails.getId().intValue()))
            .andExpect(jsonPath("$.pagedetailid").value(DEFAULT_PAGEDETAILID))
            .andExpect(jsonPath("$.pagetypename").value(DEFAULT_PAGETYPENAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEntityPageDetails() throws Exception {
        // Get the entityPageDetails
        restEntityPageDetailsMockMvc.perform(get("/api/entity-page-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityPageDetails() throws Exception {
        // Initialize the database
        entityPageDetailsRepository.saveAndFlush(entityPageDetails);

        int databaseSizeBeforeUpdate = entityPageDetailsRepository.findAll().size();

        // Update the entityPageDetails
        EntityPageDetails updatedEntityPageDetails = entityPageDetailsRepository.findById(entityPageDetails.getId()).get();
        // Disconnect from session so that the updates on updatedEntityPageDetails are not directly saved in db
        em.detach(updatedEntityPageDetails);
        updatedEntityPageDetails
            .pagedetailid(UPDATED_PAGEDETAILID)
            .pagetypename(UPDATED_PAGETYPENAME);
        EntityPageDetailsDTO entityPageDetailsDTO = entityPageDetailsMapper.toDto(updatedEntityPageDetails);

        restEntityPageDetailsMockMvc.perform(put("/api/entity-page-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityPageDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the EntityPageDetails in the database
        List<EntityPageDetails> entityPageDetailsList = entityPageDetailsRepository.findAll();
        assertThat(entityPageDetailsList).hasSize(databaseSizeBeforeUpdate);
        EntityPageDetails testEntityPageDetails = entityPageDetailsList.get(entityPageDetailsList.size() - 1);
        assertThat(testEntityPageDetails.getPagedetailid()).isEqualTo(UPDATED_PAGEDETAILID);
        assertThat(testEntityPageDetails.getPagetypename()).isEqualTo(UPDATED_PAGETYPENAME);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityPageDetails() throws Exception {
        int databaseSizeBeforeUpdate = entityPageDetailsRepository.findAll().size();

        // Create the EntityPageDetails
        EntityPageDetailsDTO entityPageDetailsDTO = entityPageDetailsMapper.toDto(entityPageDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntityPageDetailsMockMvc.perform(put("/api/entity-page-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityPageDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityPageDetails in the database
        List<EntityPageDetails> entityPageDetailsList = entityPageDetailsRepository.findAll();
        assertThat(entityPageDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntityPageDetails() throws Exception {
        // Initialize the database
        entityPageDetailsRepository.saveAndFlush(entityPageDetails);

        int databaseSizeBeforeDelete = entityPageDetailsRepository.findAll().size();

        // Get the entityPageDetails
        restEntityPageDetailsMockMvc.perform(delete("/api/entity-page-details/{id}", entityPageDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EntityPageDetails> entityPageDetailsList = entityPageDetailsRepository.findAll();
        assertThat(entityPageDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityPageDetails.class);
        EntityPageDetails entityPageDetails1 = new EntityPageDetails();
        entityPageDetails1.setId(1L);
        EntityPageDetails entityPageDetails2 = new EntityPageDetails();
        entityPageDetails2.setId(entityPageDetails1.getId());
        assertThat(entityPageDetails1).isEqualTo(entityPageDetails2);
        entityPageDetails2.setId(2L);
        assertThat(entityPageDetails1).isNotEqualTo(entityPageDetails2);
        entityPageDetails1.setId(null);
        assertThat(entityPageDetails1).isNotEqualTo(entityPageDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityPageDetailsDTO.class);
        EntityPageDetailsDTO entityPageDetailsDTO1 = new EntityPageDetailsDTO();
        entityPageDetailsDTO1.setId(1L);
        EntityPageDetailsDTO entityPageDetailsDTO2 = new EntityPageDetailsDTO();
        assertThat(entityPageDetailsDTO1).isNotEqualTo(entityPageDetailsDTO2);
        entityPageDetailsDTO2.setId(entityPageDetailsDTO1.getId());
        assertThat(entityPageDetailsDTO1).isEqualTo(entityPageDetailsDTO2);
        entityPageDetailsDTO2.setId(2L);
        assertThat(entityPageDetailsDTO1).isNotEqualTo(entityPageDetailsDTO2);
        entityPageDetailsDTO1.setId(null);
        assertThat(entityPageDetailsDTO1).isNotEqualTo(entityPageDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entityPageDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entityPageDetailsMapper.fromId(null)).isNull();
    }
}
