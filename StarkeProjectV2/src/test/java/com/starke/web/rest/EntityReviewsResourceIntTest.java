package com.starke.web.rest;

import com.starke.Starkev2App;

import com.starke.domain.EntityReviews;
import com.starke.repository.EntityReviewsRepository;
import com.starke.service.EntityReviewsService;
import com.starke.service.dto.EntityReviewsDTO;
import com.starke.service.mapper.EntityReviewsMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.starke.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EntityReviewsResource REST controller.
 *
 * @see EntityReviewsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starkev2App.class)
public class EntityReviewsResourceIntTest {

    private static final Integer DEFAULT_RATING = 1;
    private static final Integer UPDATED_RATING = 2;

    private static final Integer DEFAULT_REVIEWID = 1;
    private static final Integer UPDATED_REVIEWID = 2;

    private static final Integer DEFAULT_ENTITYID = 1;
    private static final Integer UPDATED_ENTITYID = 2;

    private static final LocalDate DEFAULT_REVIEWDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REVIEWDATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_USERID = 1;
    private static final Integer UPDATED_USERID = 2;

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_DETAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_ISMARKED = 1;
    private static final Integer UPDATED_ISMARKED = 2;

    private static final Integer DEFAULT_ISDELETED = 1;
    private static final Integer UPDATED_ISDELETED = 2;

    @Autowired
    private EntityReviewsRepository entityReviewsRepository;


    @Autowired
    private EntityReviewsMapper entityReviewsMapper;
    

    @Autowired
    private EntityReviewsService entityReviewsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEntityReviewsMockMvc;

    private EntityReviews entityReviews;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntityReviewsResource entityReviewsResource = new EntityReviewsResource(entityReviewsService);
        this.restEntityReviewsMockMvc = MockMvcBuilders.standaloneSetup(entityReviewsResource)
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
    public static EntityReviews createEntity(EntityManager em) {
        EntityReviews entityReviews = new EntityReviews()
            .rating(DEFAULT_RATING)
            .reviewid(DEFAULT_REVIEWID)
            .entityid(DEFAULT_ENTITYID)
            .reviewdate(DEFAULT_REVIEWDATE)
            .userid(DEFAULT_USERID)
            .subject(DEFAULT_SUBJECT)
            .detail(DEFAULT_DETAIL)
            .ismarked(DEFAULT_ISMARKED)
            .isdeleted(DEFAULT_ISDELETED);
        return entityReviews;
    }

    @Before
    public void initTest() {
        entityReviews = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityReviews() throws Exception {
        int databaseSizeBeforeCreate = entityReviewsRepository.findAll().size();

        // Create the EntityReviews
        EntityReviewsDTO entityReviewsDTO = entityReviewsMapper.toDto(entityReviews);
        restEntityReviewsMockMvc.perform(post("/api/entity-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityReviewsDTO)))
            .andExpect(status().isCreated());

        // Validate the EntityReviews in the database
        List<EntityReviews> entityReviewsList = entityReviewsRepository.findAll();
        assertThat(entityReviewsList).hasSize(databaseSizeBeforeCreate + 1);
        EntityReviews testEntityReviews = entityReviewsList.get(entityReviewsList.size() - 1);
        assertThat(testEntityReviews.getRating()).isEqualTo(DEFAULT_RATING);
        assertThat(testEntityReviews.getReviewid()).isEqualTo(DEFAULT_REVIEWID);
        assertThat(testEntityReviews.getEntityid()).isEqualTo(DEFAULT_ENTITYID);
        assertThat(testEntityReviews.getReviewdate()).isEqualTo(DEFAULT_REVIEWDATE);
        assertThat(testEntityReviews.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testEntityReviews.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testEntityReviews.getDetail()).isEqualTo(DEFAULT_DETAIL);
        assertThat(testEntityReviews.getIsmarked()).isEqualTo(DEFAULT_ISMARKED);
        assertThat(testEntityReviews.getIsdeleted()).isEqualTo(DEFAULT_ISDELETED);
    }

    @Test
    @Transactional
    public void createEntityReviewsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityReviewsRepository.findAll().size();

        // Create the EntityReviews with an existing ID
        entityReviews.setId(1L);
        EntityReviewsDTO entityReviewsDTO = entityReviewsMapper.toDto(entityReviews);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityReviewsMockMvc.perform(post("/api/entity-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityReviewsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityReviews in the database
        List<EntityReviews> entityReviewsList = entityReviewsRepository.findAll();
        assertThat(entityReviewsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkReviewidIsRequired() throws Exception {
        int databaseSizeBeforeTest = entityReviewsRepository.findAll().size();
        // set the field null
        entityReviews.setReviewid(null);

        // Create the EntityReviews, which fails.
        EntityReviewsDTO entityReviewsDTO = entityReviewsMapper.toDto(entityReviews);

        restEntityReviewsMockMvc.perform(post("/api/entity-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityReviewsDTO)))
            .andExpect(status().isBadRequest());

        List<EntityReviews> entityReviewsList = entityReviewsRepository.findAll();
        assertThat(entityReviewsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEntityidIsRequired() throws Exception {
        int databaseSizeBeforeTest = entityReviewsRepository.findAll().size();
        // set the field null
        entityReviews.setEntityid(null);

        // Create the EntityReviews, which fails.
        EntityReviewsDTO entityReviewsDTO = entityReviewsMapper.toDto(entityReviews);

        restEntityReviewsMockMvc.perform(post("/api/entity-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityReviewsDTO)))
            .andExpect(status().isBadRequest());

        List<EntityReviews> entityReviewsList = entityReviewsRepository.findAll();
        assertThat(entityReviewsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUseridIsRequired() throws Exception {
        int databaseSizeBeforeTest = entityReviewsRepository.findAll().size();
        // set the field null
        entityReviews.setUserid(null);

        // Create the EntityReviews, which fails.
        EntityReviewsDTO entityReviewsDTO = entityReviewsMapper.toDto(entityReviews);

        restEntityReviewsMockMvc.perform(post("/api/entity-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityReviewsDTO)))
            .andExpect(status().isBadRequest());

        List<EntityReviews> entityReviewsList = entityReviewsRepository.findAll();
        assertThat(entityReviewsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsdeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = entityReviewsRepository.findAll().size();
        // set the field null
        entityReviews.setIsdeleted(null);

        // Create the EntityReviews, which fails.
        EntityReviewsDTO entityReviewsDTO = entityReviewsMapper.toDto(entityReviews);

        restEntityReviewsMockMvc.perform(post("/api/entity-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityReviewsDTO)))
            .andExpect(status().isBadRequest());

        List<EntityReviews> entityReviewsList = entityReviewsRepository.findAll();
        assertThat(entityReviewsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEntityReviews() throws Exception {
        // Initialize the database
        entityReviewsRepository.saveAndFlush(entityReviews);

        // Get all the entityReviewsList
        restEntityReviewsMockMvc.perform(get("/api/entity-reviews?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityReviews.getId().intValue())))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)))
            .andExpect(jsonPath("$.[*].reviewid").value(hasItem(DEFAULT_REVIEWID)))
            .andExpect(jsonPath("$.[*].entityid").value(hasItem(DEFAULT_ENTITYID)))
            .andExpect(jsonPath("$.[*].reviewdate").value(hasItem(DEFAULT_REVIEWDATE.toString())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT.toString())))
            .andExpect(jsonPath("$.[*].detail").value(hasItem(DEFAULT_DETAIL.toString())))
            .andExpect(jsonPath("$.[*].ismarked").value(hasItem(DEFAULT_ISMARKED)))
            .andExpect(jsonPath("$.[*].isdeleted").value(hasItem(DEFAULT_ISDELETED)));
    }
    

    @Test
    @Transactional
    public void getEntityReviews() throws Exception {
        // Initialize the database
        entityReviewsRepository.saveAndFlush(entityReviews);

        // Get the entityReviews
        restEntityReviewsMockMvc.perform(get("/api/entity-reviews/{id}", entityReviews.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityReviews.getId().intValue()))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING))
            .andExpect(jsonPath("$.reviewid").value(DEFAULT_REVIEWID))
            .andExpect(jsonPath("$.entityid").value(DEFAULT_ENTITYID))
            .andExpect(jsonPath("$.reviewdate").value(DEFAULT_REVIEWDATE.toString()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT.toString()))
            .andExpect(jsonPath("$.detail").value(DEFAULT_DETAIL.toString()))
            .andExpect(jsonPath("$.ismarked").value(DEFAULT_ISMARKED))
            .andExpect(jsonPath("$.isdeleted").value(DEFAULT_ISDELETED));
    }
    @Test
    @Transactional
    public void getNonExistingEntityReviews() throws Exception {
        // Get the entityReviews
        restEntityReviewsMockMvc.perform(get("/api/entity-reviews/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityReviews() throws Exception {
        // Initialize the database
        entityReviewsRepository.saveAndFlush(entityReviews);

        int databaseSizeBeforeUpdate = entityReviewsRepository.findAll().size();

        // Update the entityReviews
        EntityReviews updatedEntityReviews = entityReviewsRepository.findById(entityReviews.getId()).get();
        // Disconnect from session so that the updates on updatedEntityReviews are not directly saved in db
        em.detach(updatedEntityReviews);
        updatedEntityReviews
            .rating(UPDATED_RATING)
            .reviewid(UPDATED_REVIEWID)
            .entityid(UPDATED_ENTITYID)
            .reviewdate(UPDATED_REVIEWDATE)
            .userid(UPDATED_USERID)
            .subject(UPDATED_SUBJECT)
            .detail(UPDATED_DETAIL)
            .ismarked(UPDATED_ISMARKED)
            .isdeleted(UPDATED_ISDELETED);
        EntityReviewsDTO entityReviewsDTO = entityReviewsMapper.toDto(updatedEntityReviews);

        restEntityReviewsMockMvc.perform(put("/api/entity-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityReviewsDTO)))
            .andExpect(status().isOk());

        // Validate the EntityReviews in the database
        List<EntityReviews> entityReviewsList = entityReviewsRepository.findAll();
        assertThat(entityReviewsList).hasSize(databaseSizeBeforeUpdate);
        EntityReviews testEntityReviews = entityReviewsList.get(entityReviewsList.size() - 1);
        assertThat(testEntityReviews.getRating()).isEqualTo(UPDATED_RATING);
        assertThat(testEntityReviews.getReviewid()).isEqualTo(UPDATED_REVIEWID);
        assertThat(testEntityReviews.getEntityid()).isEqualTo(UPDATED_ENTITYID);
        assertThat(testEntityReviews.getReviewdate()).isEqualTo(UPDATED_REVIEWDATE);
        assertThat(testEntityReviews.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testEntityReviews.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testEntityReviews.getDetail()).isEqualTo(UPDATED_DETAIL);
        assertThat(testEntityReviews.getIsmarked()).isEqualTo(UPDATED_ISMARKED);
        assertThat(testEntityReviews.getIsdeleted()).isEqualTo(UPDATED_ISDELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityReviews() throws Exception {
        int databaseSizeBeforeUpdate = entityReviewsRepository.findAll().size();

        // Create the EntityReviews
        EntityReviewsDTO entityReviewsDTO = entityReviewsMapper.toDto(entityReviews);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntityReviewsMockMvc.perform(put("/api/entity-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityReviewsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityReviews in the database
        List<EntityReviews> entityReviewsList = entityReviewsRepository.findAll();
        assertThat(entityReviewsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntityReviews() throws Exception {
        // Initialize the database
        entityReviewsRepository.saveAndFlush(entityReviews);

        int databaseSizeBeforeDelete = entityReviewsRepository.findAll().size();

        // Get the entityReviews
        restEntityReviewsMockMvc.perform(delete("/api/entity-reviews/{id}", entityReviews.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EntityReviews> entityReviewsList = entityReviewsRepository.findAll();
        assertThat(entityReviewsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityReviews.class);
        EntityReviews entityReviews1 = new EntityReviews();
        entityReviews1.setId(1L);
        EntityReviews entityReviews2 = new EntityReviews();
        entityReviews2.setId(entityReviews1.getId());
        assertThat(entityReviews1).isEqualTo(entityReviews2);
        entityReviews2.setId(2L);
        assertThat(entityReviews1).isNotEqualTo(entityReviews2);
        entityReviews1.setId(null);
        assertThat(entityReviews1).isNotEqualTo(entityReviews2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityReviewsDTO.class);
        EntityReviewsDTO entityReviewsDTO1 = new EntityReviewsDTO();
        entityReviewsDTO1.setId(1L);
        EntityReviewsDTO entityReviewsDTO2 = new EntityReviewsDTO();
        assertThat(entityReviewsDTO1).isNotEqualTo(entityReviewsDTO2);
        entityReviewsDTO2.setId(entityReviewsDTO1.getId());
        assertThat(entityReviewsDTO1).isEqualTo(entityReviewsDTO2);
        entityReviewsDTO2.setId(2L);
        assertThat(entityReviewsDTO1).isNotEqualTo(entityReviewsDTO2);
        entityReviewsDTO1.setId(null);
        assertThat(entityReviewsDTO1).isNotEqualTo(entityReviewsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entityReviewsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entityReviewsMapper.fromId(null)).isNull();
    }
}
