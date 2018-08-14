package com.starke.web.rest;

import com.starke.Starkev2App;

import com.starke.domain.EntityDetails;
import com.starke.repository.EntityDetailsRepository;
import com.starke.service.EntityDetailsService;
import com.starke.service.dto.EntityDetailsDTO;
import com.starke.service.mapper.EntityDetailsMapper;
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
 * Test class for the EntityDetailsResource REST controller.
 *
 * @see EntityDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starkev2App.class)
public class EntityDetailsResourceIntTest {

    private static final Integer DEFAULT_ENTITYID = 1;
    private static final Integer UPDATED_ENTITYID = 2;

    private static final String DEFAULT_ENTITYDESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ENTITYDESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ENTITYCONTENT = "AAAAAAAAAA";
    private static final String UPDATED_ENTITYCONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_ENTITYCONTENT_2 = "AAAAAAAAAA";
    private static final String UPDATED_ENTITYCONTENT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ENTITYCONTENT_3 = "AAAAAAAAAA";
    private static final String UPDATED_ENTITYCONTENT_3 = "BBBBBBBBBB";

    @Autowired
    private EntityDetailsRepository entityDetailsRepository;


    @Autowired
    private EntityDetailsMapper entityDetailsMapper;
    

    @Autowired
    private EntityDetailsService entityDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEntityDetailsMockMvc;

    private EntityDetails entityDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntityDetailsResource entityDetailsResource = new EntityDetailsResource(entityDetailsService);
        this.restEntityDetailsMockMvc = MockMvcBuilders.standaloneSetup(entityDetailsResource)
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
    public static EntityDetails createEntity(EntityManager em) {
        EntityDetails entityDetails = new EntityDetails()
            .entityid(DEFAULT_ENTITYID)
            .entitydescription(DEFAULT_ENTITYDESCRIPTION)
            .entitycontent(DEFAULT_ENTITYCONTENT)
            .entitycontent2(DEFAULT_ENTITYCONTENT_2)
            .entitycontent3(DEFAULT_ENTITYCONTENT_3);
        return entityDetails;
    }

    @Before
    public void initTest() {
        entityDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityDetails() throws Exception {
        int databaseSizeBeforeCreate = entityDetailsRepository.findAll().size();

        // Create the EntityDetails
        EntityDetailsDTO entityDetailsDTO = entityDetailsMapper.toDto(entityDetails);
        restEntityDetailsMockMvc.perform(post("/api/entity-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the EntityDetails in the database
        List<EntityDetails> entityDetailsList = entityDetailsRepository.findAll();
        assertThat(entityDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        EntityDetails testEntityDetails = entityDetailsList.get(entityDetailsList.size() - 1);
        assertThat(testEntityDetails.getEntityid()).isEqualTo(DEFAULT_ENTITYID);
        assertThat(testEntityDetails.getEntitydescription()).isEqualTo(DEFAULT_ENTITYDESCRIPTION);
        assertThat(testEntityDetails.getEntitycontent()).isEqualTo(DEFAULT_ENTITYCONTENT);
        assertThat(testEntityDetails.getEntitycontent2()).isEqualTo(DEFAULT_ENTITYCONTENT_2);
        assertThat(testEntityDetails.getEntitycontent3()).isEqualTo(DEFAULT_ENTITYCONTENT_3);
    }

    @Test
    @Transactional
    public void createEntityDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityDetailsRepository.findAll().size();

        // Create the EntityDetails with an existing ID
        entityDetails.setId(1L);
        EntityDetailsDTO entityDetailsDTO = entityDetailsMapper.toDto(entityDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityDetailsMockMvc.perform(post("/api/entity-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityDetails in the database
        List<EntityDetails> entityDetailsList = entityDetailsRepository.findAll();
        assertThat(entityDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEntityidIsRequired() throws Exception {
        int databaseSizeBeforeTest = entityDetailsRepository.findAll().size();
        // set the field null
        entityDetails.setEntityid(null);

        // Create the EntityDetails, which fails.
        EntityDetailsDTO entityDetailsDTO = entityDetailsMapper.toDto(entityDetails);

        restEntityDetailsMockMvc.perform(post("/api/entity-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<EntityDetails> entityDetailsList = entityDetailsRepository.findAll();
        assertThat(entityDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEntityDetails() throws Exception {
        // Initialize the database
        entityDetailsRepository.saveAndFlush(entityDetails);

        // Get all the entityDetailsList
        restEntityDetailsMockMvc.perform(get("/api/entity-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].entityid").value(hasItem(DEFAULT_ENTITYID)))
            .andExpect(jsonPath("$.[*].entitydescription").value(hasItem(DEFAULT_ENTITYDESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].entitycontent").value(hasItem(DEFAULT_ENTITYCONTENT.toString())))
            .andExpect(jsonPath("$.[*].entitycontent2").value(hasItem(DEFAULT_ENTITYCONTENT_2.toString())))
            .andExpect(jsonPath("$.[*].entitycontent3").value(hasItem(DEFAULT_ENTITYCONTENT_3.toString())));
    }
    

    @Test
    @Transactional
    public void getEntityDetails() throws Exception {
        // Initialize the database
        entityDetailsRepository.saveAndFlush(entityDetails);

        // Get the entityDetails
        restEntityDetailsMockMvc.perform(get("/api/entity-details/{id}", entityDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityDetails.getId().intValue()))
            .andExpect(jsonPath("$.entityid").value(DEFAULT_ENTITYID))
            .andExpect(jsonPath("$.entitydescription").value(DEFAULT_ENTITYDESCRIPTION.toString()))
            .andExpect(jsonPath("$.entitycontent").value(DEFAULT_ENTITYCONTENT.toString()))
            .andExpect(jsonPath("$.entitycontent2").value(DEFAULT_ENTITYCONTENT_2.toString()))
            .andExpect(jsonPath("$.entitycontent3").value(DEFAULT_ENTITYCONTENT_3.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEntityDetails() throws Exception {
        // Get the entityDetails
        restEntityDetailsMockMvc.perform(get("/api/entity-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityDetails() throws Exception {
        // Initialize the database
        entityDetailsRepository.saveAndFlush(entityDetails);

        int databaseSizeBeforeUpdate = entityDetailsRepository.findAll().size();

        // Update the entityDetails
        EntityDetails updatedEntityDetails = entityDetailsRepository.findById(entityDetails.getId()).get();
        // Disconnect from session so that the updates on updatedEntityDetails are not directly saved in db
        em.detach(updatedEntityDetails);
        updatedEntityDetails
            .entityid(UPDATED_ENTITYID)
            .entitydescription(UPDATED_ENTITYDESCRIPTION)
            .entitycontent(UPDATED_ENTITYCONTENT)
            .entitycontent2(UPDATED_ENTITYCONTENT_2)
            .entitycontent3(UPDATED_ENTITYCONTENT_3);
        EntityDetailsDTO entityDetailsDTO = entityDetailsMapper.toDto(updatedEntityDetails);

        restEntityDetailsMockMvc.perform(put("/api/entity-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the EntityDetails in the database
        List<EntityDetails> entityDetailsList = entityDetailsRepository.findAll();
        assertThat(entityDetailsList).hasSize(databaseSizeBeforeUpdate);
        EntityDetails testEntityDetails = entityDetailsList.get(entityDetailsList.size() - 1);
        assertThat(testEntityDetails.getEntityid()).isEqualTo(UPDATED_ENTITYID);
        assertThat(testEntityDetails.getEntitydescription()).isEqualTo(UPDATED_ENTITYDESCRIPTION);
        assertThat(testEntityDetails.getEntitycontent()).isEqualTo(UPDATED_ENTITYCONTENT);
        assertThat(testEntityDetails.getEntitycontent2()).isEqualTo(UPDATED_ENTITYCONTENT_2);
        assertThat(testEntityDetails.getEntitycontent3()).isEqualTo(UPDATED_ENTITYCONTENT_3);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityDetails() throws Exception {
        int databaseSizeBeforeUpdate = entityDetailsRepository.findAll().size();

        // Create the EntityDetails
        EntityDetailsDTO entityDetailsDTO = entityDetailsMapper.toDto(entityDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntityDetailsMockMvc.perform(put("/api/entity-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityDetails in the database
        List<EntityDetails> entityDetailsList = entityDetailsRepository.findAll();
        assertThat(entityDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntityDetails() throws Exception {
        // Initialize the database
        entityDetailsRepository.saveAndFlush(entityDetails);

        int databaseSizeBeforeDelete = entityDetailsRepository.findAll().size();

        // Get the entityDetails
        restEntityDetailsMockMvc.perform(delete("/api/entity-details/{id}", entityDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EntityDetails> entityDetailsList = entityDetailsRepository.findAll();
        assertThat(entityDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityDetails.class);
        EntityDetails entityDetails1 = new EntityDetails();
        entityDetails1.setId(1L);
        EntityDetails entityDetails2 = new EntityDetails();
        entityDetails2.setId(entityDetails1.getId());
        assertThat(entityDetails1).isEqualTo(entityDetails2);
        entityDetails2.setId(2L);
        assertThat(entityDetails1).isNotEqualTo(entityDetails2);
        entityDetails1.setId(null);
        assertThat(entityDetails1).isNotEqualTo(entityDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityDetailsDTO.class);
        EntityDetailsDTO entityDetailsDTO1 = new EntityDetailsDTO();
        entityDetailsDTO1.setId(1L);
        EntityDetailsDTO entityDetailsDTO2 = new EntityDetailsDTO();
        assertThat(entityDetailsDTO1).isNotEqualTo(entityDetailsDTO2);
        entityDetailsDTO2.setId(entityDetailsDTO1.getId());
        assertThat(entityDetailsDTO1).isEqualTo(entityDetailsDTO2);
        entityDetailsDTO2.setId(2L);
        assertThat(entityDetailsDTO1).isNotEqualTo(entityDetailsDTO2);
        entityDetailsDTO1.setId(null);
        assertThat(entityDetailsDTO1).isNotEqualTo(entityDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entityDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entityDetailsMapper.fromId(null)).isNull();
    }
}
