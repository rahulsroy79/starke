package com.starke.web.rest;

import com.starke.Starkev2App;

import com.starke.domain.EntityPageFormats;
import com.starke.repository.EntityPageFormatsRepository;
import com.starke.service.EntityPageFormatsService;
import com.starke.service.dto.EntityPageFormatsDTO;
import com.starke.service.mapper.EntityPageFormatsMapper;
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
 * Test class for the EntityPageFormatsResource REST controller.
 *
 * @see EntityPageFormatsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starkev2App.class)
public class EntityPageFormatsResourceIntTest {

    private static final Integer DEFAULT_PAGEFORMATID = 1;
    private static final Integer UPDATED_PAGEFORMATID = 2;

    private static final String DEFAULT_PAGEFORMATNAME = "AAAAAAAAAA";
    private static final String UPDATED_PAGEFORMATNAME = "BBBBBBBBBB";

    private static final String DEFAULT_MAINIMAGEPATH = "AAAAAAAAAA";
    private static final String UPDATED_MAINIMAGEPATH = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_2_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_2_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_3_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_3_PATH = "BBBBBBBBBB";

    @Autowired
    private EntityPageFormatsRepository entityPageFormatsRepository;


    @Autowired
    private EntityPageFormatsMapper entityPageFormatsMapper;
    

    @Autowired
    private EntityPageFormatsService entityPageFormatsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEntityPageFormatsMockMvc;

    private EntityPageFormats entityPageFormats;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntityPageFormatsResource entityPageFormatsResource = new EntityPageFormatsResource(entityPageFormatsService);
        this.restEntityPageFormatsMockMvc = MockMvcBuilders.standaloneSetup(entityPageFormatsResource)
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
    public static EntityPageFormats createEntity(EntityManager em) {
        EntityPageFormats entityPageFormats = new EntityPageFormats()
            .pageformatid(DEFAULT_PAGEFORMATID)
            .pageformatname(DEFAULT_PAGEFORMATNAME)
            .mainimagepath(DEFAULT_MAINIMAGEPATH)
            .image2path(DEFAULT_IMAGE_2_PATH)
            .image3path(DEFAULT_IMAGE_3_PATH);
        return entityPageFormats;
    }

    @Before
    public void initTest() {
        entityPageFormats = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityPageFormats() throws Exception {
        int databaseSizeBeforeCreate = entityPageFormatsRepository.findAll().size();

        // Create the EntityPageFormats
        EntityPageFormatsDTO entityPageFormatsDTO = entityPageFormatsMapper.toDto(entityPageFormats);
        restEntityPageFormatsMockMvc.perform(post("/api/entity-page-formats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityPageFormatsDTO)))
            .andExpect(status().isCreated());

        // Validate the EntityPageFormats in the database
        List<EntityPageFormats> entityPageFormatsList = entityPageFormatsRepository.findAll();
        assertThat(entityPageFormatsList).hasSize(databaseSizeBeforeCreate + 1);
        EntityPageFormats testEntityPageFormats = entityPageFormatsList.get(entityPageFormatsList.size() - 1);
        assertThat(testEntityPageFormats.getPageformatid()).isEqualTo(DEFAULT_PAGEFORMATID);
        assertThat(testEntityPageFormats.getPageformatname()).isEqualTo(DEFAULT_PAGEFORMATNAME);
        assertThat(testEntityPageFormats.getMainimagepath()).isEqualTo(DEFAULT_MAINIMAGEPATH);
        assertThat(testEntityPageFormats.getImage2path()).isEqualTo(DEFAULT_IMAGE_2_PATH);
        assertThat(testEntityPageFormats.getImage3path()).isEqualTo(DEFAULT_IMAGE_3_PATH);
    }

    @Test
    @Transactional
    public void createEntityPageFormatsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityPageFormatsRepository.findAll().size();

        // Create the EntityPageFormats with an existing ID
        entityPageFormats.setId(1L);
        EntityPageFormatsDTO entityPageFormatsDTO = entityPageFormatsMapper.toDto(entityPageFormats);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityPageFormatsMockMvc.perform(post("/api/entity-page-formats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityPageFormatsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityPageFormats in the database
        List<EntityPageFormats> entityPageFormatsList = entityPageFormatsRepository.findAll();
        assertThat(entityPageFormatsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPageformatidIsRequired() throws Exception {
        int databaseSizeBeforeTest = entityPageFormatsRepository.findAll().size();
        // set the field null
        entityPageFormats.setPageformatid(null);

        // Create the EntityPageFormats, which fails.
        EntityPageFormatsDTO entityPageFormatsDTO = entityPageFormatsMapper.toDto(entityPageFormats);

        restEntityPageFormatsMockMvc.perform(post("/api/entity-page-formats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityPageFormatsDTO)))
            .andExpect(status().isBadRequest());

        List<EntityPageFormats> entityPageFormatsList = entityPageFormatsRepository.findAll();
        assertThat(entityPageFormatsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEntityPageFormats() throws Exception {
        // Initialize the database
        entityPageFormatsRepository.saveAndFlush(entityPageFormats);

        // Get all the entityPageFormatsList
        restEntityPageFormatsMockMvc.perform(get("/api/entity-page-formats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityPageFormats.getId().intValue())))
            .andExpect(jsonPath("$.[*].pageformatid").value(hasItem(DEFAULT_PAGEFORMATID)))
            .andExpect(jsonPath("$.[*].pageformatname").value(hasItem(DEFAULT_PAGEFORMATNAME.toString())))
            .andExpect(jsonPath("$.[*].mainimagepath").value(hasItem(DEFAULT_MAINIMAGEPATH.toString())))
            .andExpect(jsonPath("$.[*].image2path").value(hasItem(DEFAULT_IMAGE_2_PATH.toString())))
            .andExpect(jsonPath("$.[*].image3path").value(hasItem(DEFAULT_IMAGE_3_PATH.toString())));
    }
    

    @Test
    @Transactional
    public void getEntityPageFormats() throws Exception {
        // Initialize the database
        entityPageFormatsRepository.saveAndFlush(entityPageFormats);

        // Get the entityPageFormats
        restEntityPageFormatsMockMvc.perform(get("/api/entity-page-formats/{id}", entityPageFormats.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityPageFormats.getId().intValue()))
            .andExpect(jsonPath("$.pageformatid").value(DEFAULT_PAGEFORMATID))
            .andExpect(jsonPath("$.pageformatname").value(DEFAULT_PAGEFORMATNAME.toString()))
            .andExpect(jsonPath("$.mainimagepath").value(DEFAULT_MAINIMAGEPATH.toString()))
            .andExpect(jsonPath("$.image2path").value(DEFAULT_IMAGE_2_PATH.toString()))
            .andExpect(jsonPath("$.image3path").value(DEFAULT_IMAGE_3_PATH.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEntityPageFormats() throws Exception {
        // Get the entityPageFormats
        restEntityPageFormatsMockMvc.perform(get("/api/entity-page-formats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityPageFormats() throws Exception {
        // Initialize the database
        entityPageFormatsRepository.saveAndFlush(entityPageFormats);

        int databaseSizeBeforeUpdate = entityPageFormatsRepository.findAll().size();

        // Update the entityPageFormats
        EntityPageFormats updatedEntityPageFormats = entityPageFormatsRepository.findById(entityPageFormats.getId()).get();
        // Disconnect from session so that the updates on updatedEntityPageFormats are not directly saved in db
        em.detach(updatedEntityPageFormats);
        updatedEntityPageFormats
            .pageformatid(UPDATED_PAGEFORMATID)
            .pageformatname(UPDATED_PAGEFORMATNAME)
            .mainimagepath(UPDATED_MAINIMAGEPATH)
            .image2path(UPDATED_IMAGE_2_PATH)
            .image3path(UPDATED_IMAGE_3_PATH);
        EntityPageFormatsDTO entityPageFormatsDTO = entityPageFormatsMapper.toDto(updatedEntityPageFormats);

        restEntityPageFormatsMockMvc.perform(put("/api/entity-page-formats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityPageFormatsDTO)))
            .andExpect(status().isOk());

        // Validate the EntityPageFormats in the database
        List<EntityPageFormats> entityPageFormatsList = entityPageFormatsRepository.findAll();
        assertThat(entityPageFormatsList).hasSize(databaseSizeBeforeUpdate);
        EntityPageFormats testEntityPageFormats = entityPageFormatsList.get(entityPageFormatsList.size() - 1);
        assertThat(testEntityPageFormats.getPageformatid()).isEqualTo(UPDATED_PAGEFORMATID);
        assertThat(testEntityPageFormats.getPageformatname()).isEqualTo(UPDATED_PAGEFORMATNAME);
        assertThat(testEntityPageFormats.getMainimagepath()).isEqualTo(UPDATED_MAINIMAGEPATH);
        assertThat(testEntityPageFormats.getImage2path()).isEqualTo(UPDATED_IMAGE_2_PATH);
        assertThat(testEntityPageFormats.getImage3path()).isEqualTo(UPDATED_IMAGE_3_PATH);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityPageFormats() throws Exception {
        int databaseSizeBeforeUpdate = entityPageFormatsRepository.findAll().size();

        // Create the EntityPageFormats
        EntityPageFormatsDTO entityPageFormatsDTO = entityPageFormatsMapper.toDto(entityPageFormats);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntityPageFormatsMockMvc.perform(put("/api/entity-page-formats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityPageFormatsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityPageFormats in the database
        List<EntityPageFormats> entityPageFormatsList = entityPageFormatsRepository.findAll();
        assertThat(entityPageFormatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntityPageFormats() throws Exception {
        // Initialize the database
        entityPageFormatsRepository.saveAndFlush(entityPageFormats);

        int databaseSizeBeforeDelete = entityPageFormatsRepository.findAll().size();

        // Get the entityPageFormats
        restEntityPageFormatsMockMvc.perform(delete("/api/entity-page-formats/{id}", entityPageFormats.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EntityPageFormats> entityPageFormatsList = entityPageFormatsRepository.findAll();
        assertThat(entityPageFormatsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityPageFormats.class);
        EntityPageFormats entityPageFormats1 = new EntityPageFormats();
        entityPageFormats1.setId(1L);
        EntityPageFormats entityPageFormats2 = new EntityPageFormats();
        entityPageFormats2.setId(entityPageFormats1.getId());
        assertThat(entityPageFormats1).isEqualTo(entityPageFormats2);
        entityPageFormats2.setId(2L);
        assertThat(entityPageFormats1).isNotEqualTo(entityPageFormats2);
        entityPageFormats1.setId(null);
        assertThat(entityPageFormats1).isNotEqualTo(entityPageFormats2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityPageFormatsDTO.class);
        EntityPageFormatsDTO entityPageFormatsDTO1 = new EntityPageFormatsDTO();
        entityPageFormatsDTO1.setId(1L);
        EntityPageFormatsDTO entityPageFormatsDTO2 = new EntityPageFormatsDTO();
        assertThat(entityPageFormatsDTO1).isNotEqualTo(entityPageFormatsDTO2);
        entityPageFormatsDTO2.setId(entityPageFormatsDTO1.getId());
        assertThat(entityPageFormatsDTO1).isEqualTo(entityPageFormatsDTO2);
        entityPageFormatsDTO2.setId(2L);
        assertThat(entityPageFormatsDTO1).isNotEqualTo(entityPageFormatsDTO2);
        entityPageFormatsDTO1.setId(null);
        assertThat(entityPageFormatsDTO1).isNotEqualTo(entityPageFormatsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entityPageFormatsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entityPageFormatsMapper.fromId(null)).isNull();
    }
}
