package com.starke.web.rest;

import com.starke.Starkev2App;

import com.starke.domain.StarkeEntity;
import com.starke.repository.StarkeEntityRepository;
import com.starke.service.StarkeEntityService;
import com.starke.service.dto.StarkeEntityDTO;
import com.starke.service.mapper.StarkeEntityMapper;
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
import org.springframework.util.Base64Utils;

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
 * Test class for the StarkeEntityResource REST controller.
 *
 * @see StarkeEntityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starkev2App.class)
public class StarkeEntityResourceIntTest {

    private static final Integer DEFAULT_ENTITYID = 1;
    private static final Integer UPDATED_ENTITYID = 2;

    private static final String DEFAULT_ENTITYNAME = "AAAAAAAAAA";
    private static final String UPDATED_ENTITYNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENTITYTYPEID = 1;
    private static final Integer UPDATED_ENTITYTYPEID = 2;

    private static final Integer DEFAULT_ISTOPENTITY = 1;
    private static final Integer UPDATED_ISTOPENTITY = 2;

    private static final Integer DEFAULT_PARENTENTITYID = 1;
    private static final Integer UPDATED_PARENTENTITYID = 2;

    private static final Integer DEFAULT_ALLOWRATING = 1;
    private static final Integer UPDATED_ALLOWRATING = 2;

    private static final Integer DEFAULT_ALLOWREVIEW = 1;
    private static final Integer UPDATED_ALLOWREVIEW = 2;

    private static final LocalDate DEFAULT_ENTITYCREATIONDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENTITYCREATIONDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ENTITYBRIEFDESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ENTITYBRIEFDESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_ENTITYIMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ENTITYIMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_ENTITYIMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ENTITYIMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private StarkeEntityRepository starkeEntityRepository;


    @Autowired
    private StarkeEntityMapper starkeEntityMapper;
    

    @Autowired
    private StarkeEntityService starkeEntityService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStarkeEntityMockMvc;

    private StarkeEntity starkeEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StarkeEntityResource starkeEntityResource = new StarkeEntityResource(starkeEntityService);
        this.restStarkeEntityMockMvc = MockMvcBuilders.standaloneSetup(starkeEntityResource)
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
    public static StarkeEntity createEntity(EntityManager em) {
        StarkeEntity starkeEntity = new StarkeEntity()
            .entityid(DEFAULT_ENTITYID)
            .entityname(DEFAULT_ENTITYNAME)
            .entitytypeid(DEFAULT_ENTITYTYPEID)
            .istopentity(DEFAULT_ISTOPENTITY)
            .parententityid(DEFAULT_PARENTENTITYID)
            .allowrating(DEFAULT_ALLOWRATING)
            .allowreview(DEFAULT_ALLOWREVIEW)
            .entitycreationdate(DEFAULT_ENTITYCREATIONDATE)
            .entitybriefdescription(DEFAULT_ENTITYBRIEFDESCRIPTION)
            .entityimage(DEFAULT_ENTITYIMAGE)
            .entityimageContentType(DEFAULT_ENTITYIMAGE_CONTENT_TYPE);
        return starkeEntity;
    }

    @Before
    public void initTest() {
        starkeEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createStarkeEntity() throws Exception {
        int databaseSizeBeforeCreate = starkeEntityRepository.findAll().size();

        // Create the StarkeEntity
        StarkeEntityDTO starkeEntityDTO = starkeEntityMapper.toDto(starkeEntity);
        restStarkeEntityMockMvc.perform(post("/api/starke-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeEntityDTO)))
            .andExpect(status().isCreated());

        // Validate the StarkeEntity in the database
        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeCreate + 1);
        StarkeEntity testStarkeEntity = starkeEntityList.get(starkeEntityList.size() - 1);
        assertThat(testStarkeEntity.getEntityid()).isEqualTo(DEFAULT_ENTITYID);
        assertThat(testStarkeEntity.getEntityname()).isEqualTo(DEFAULT_ENTITYNAME);
        assertThat(testStarkeEntity.getEntitytypeid()).isEqualTo(DEFAULT_ENTITYTYPEID);
        assertThat(testStarkeEntity.getIstopentity()).isEqualTo(DEFAULT_ISTOPENTITY);
        assertThat(testStarkeEntity.getParententityid()).isEqualTo(DEFAULT_PARENTENTITYID);
        assertThat(testStarkeEntity.getAllowrating()).isEqualTo(DEFAULT_ALLOWRATING);
        assertThat(testStarkeEntity.getAllowreview()).isEqualTo(DEFAULT_ALLOWREVIEW);
        assertThat(testStarkeEntity.getEntitycreationdate()).isEqualTo(DEFAULT_ENTITYCREATIONDATE);
        assertThat(testStarkeEntity.getEntitybriefdescription()).isEqualTo(DEFAULT_ENTITYBRIEFDESCRIPTION);
        assertThat(testStarkeEntity.getEntityimage()).isEqualTo(DEFAULT_ENTITYIMAGE);
        assertThat(testStarkeEntity.getEntityimageContentType()).isEqualTo(DEFAULT_ENTITYIMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createStarkeEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = starkeEntityRepository.findAll().size();

        // Create the StarkeEntity with an existing ID
        starkeEntity.setId(1L);
        StarkeEntityDTO starkeEntityDTO = starkeEntityMapper.toDto(starkeEntity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStarkeEntityMockMvc.perform(post("/api/starke-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeEntityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StarkeEntity in the database
        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEntityidIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeEntityRepository.findAll().size();
        // set the field null
        starkeEntity.setEntityid(null);

        // Create the StarkeEntity, which fails.
        StarkeEntityDTO starkeEntityDTO = starkeEntityMapper.toDto(starkeEntity);

        restStarkeEntityMockMvc.perform(post("/api/starke-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeEntityDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEntitynameIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeEntityRepository.findAll().size();
        // set the field null
        starkeEntity.setEntityname(null);

        // Create the StarkeEntity, which fails.
        StarkeEntityDTO starkeEntityDTO = starkeEntityMapper.toDto(starkeEntity);

        restStarkeEntityMockMvc.perform(post("/api/starke-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeEntityDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEntitytypeidIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeEntityRepository.findAll().size();
        // set the field null
        starkeEntity.setEntitytypeid(null);

        // Create the StarkeEntity, which fails.
        StarkeEntityDTO starkeEntityDTO = starkeEntityMapper.toDto(starkeEntity);

        restStarkeEntityMockMvc.perform(post("/api/starke-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeEntityDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIstopentityIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeEntityRepository.findAll().size();
        // set the field null
        starkeEntity.setIstopentity(null);

        // Create the StarkeEntity, which fails.
        StarkeEntityDTO starkeEntityDTO = starkeEntityMapper.toDto(starkeEntity);

        restStarkeEntityMockMvc.perform(post("/api/starke-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeEntityDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAllowratingIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeEntityRepository.findAll().size();
        // set the field null
        starkeEntity.setAllowrating(null);

        // Create the StarkeEntity, which fails.
        StarkeEntityDTO starkeEntityDTO = starkeEntityMapper.toDto(starkeEntity);

        restStarkeEntityMockMvc.perform(post("/api/starke-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeEntityDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAllowreviewIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeEntityRepository.findAll().size();
        // set the field null
        starkeEntity.setAllowreview(null);

        // Create the StarkeEntity, which fails.
        StarkeEntityDTO starkeEntityDTO = starkeEntityMapper.toDto(starkeEntity);

        restStarkeEntityMockMvc.perform(post("/api/starke-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeEntityDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEntitycreationdateIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeEntityRepository.findAll().size();
        // set the field null
        starkeEntity.setEntitycreationdate(null);

        // Create the StarkeEntity, which fails.
        StarkeEntityDTO starkeEntityDTO = starkeEntityMapper.toDto(starkeEntity);

        restStarkeEntityMockMvc.perform(post("/api/starke-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeEntityDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStarkeEntities() throws Exception {
        // Initialize the database
        starkeEntityRepository.saveAndFlush(starkeEntity);

        // Get all the starkeEntityList
        restStarkeEntityMockMvc.perform(get("/api/starke-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(starkeEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].entityid").value(hasItem(DEFAULT_ENTITYID)))
            .andExpect(jsonPath("$.[*].entityname").value(hasItem(DEFAULT_ENTITYNAME.toString())))
            .andExpect(jsonPath("$.[*].entitytypeid").value(hasItem(DEFAULT_ENTITYTYPEID)))
            .andExpect(jsonPath("$.[*].istopentity").value(hasItem(DEFAULT_ISTOPENTITY)))
            .andExpect(jsonPath("$.[*].parententityid").value(hasItem(DEFAULT_PARENTENTITYID)))
            .andExpect(jsonPath("$.[*].allowrating").value(hasItem(DEFAULT_ALLOWRATING)))
            .andExpect(jsonPath("$.[*].allowreview").value(hasItem(DEFAULT_ALLOWREVIEW)))
            .andExpect(jsonPath("$.[*].entitycreationdate").value(hasItem(DEFAULT_ENTITYCREATIONDATE.toString())))
            .andExpect(jsonPath("$.[*].entitybriefdescription").value(hasItem(DEFAULT_ENTITYBRIEFDESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].entityimageContentType").value(hasItem(DEFAULT_ENTITYIMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].entityimage").value(hasItem(Base64Utils.encodeToString(DEFAULT_ENTITYIMAGE))));
    }
    

    @Test
    @Transactional
    public void getStarkeEntity() throws Exception {
        // Initialize the database
        starkeEntityRepository.saveAndFlush(starkeEntity);

        // Get the starkeEntity
        restStarkeEntityMockMvc.perform(get("/api/starke-entities/{id}", starkeEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(starkeEntity.getId().intValue()))
            .andExpect(jsonPath("$.entityid").value(DEFAULT_ENTITYID))
            .andExpect(jsonPath("$.entityname").value(DEFAULT_ENTITYNAME.toString()))
            .andExpect(jsonPath("$.entitytypeid").value(DEFAULT_ENTITYTYPEID))
            .andExpect(jsonPath("$.istopentity").value(DEFAULT_ISTOPENTITY))
            .andExpect(jsonPath("$.parententityid").value(DEFAULT_PARENTENTITYID))
            .andExpect(jsonPath("$.allowrating").value(DEFAULT_ALLOWRATING))
            .andExpect(jsonPath("$.allowreview").value(DEFAULT_ALLOWREVIEW))
            .andExpect(jsonPath("$.entitycreationdate").value(DEFAULT_ENTITYCREATIONDATE.toString()))
            .andExpect(jsonPath("$.entitybriefdescription").value(DEFAULT_ENTITYBRIEFDESCRIPTION.toString()))
            .andExpect(jsonPath("$.entityimageContentType").value(DEFAULT_ENTITYIMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.entityimage").value(Base64Utils.encodeToString(DEFAULT_ENTITYIMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingStarkeEntity() throws Exception {
        // Get the starkeEntity
        restStarkeEntityMockMvc.perform(get("/api/starke-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStarkeEntity() throws Exception {
        // Initialize the database
        starkeEntityRepository.saveAndFlush(starkeEntity);

        int databaseSizeBeforeUpdate = starkeEntityRepository.findAll().size();

        // Update the starkeEntity
        StarkeEntity updatedStarkeEntity = starkeEntityRepository.findById(starkeEntity.getId()).get();
        // Disconnect from session so that the updates on updatedStarkeEntity are not directly saved in db
        em.detach(updatedStarkeEntity);
        updatedStarkeEntity
            .entityid(UPDATED_ENTITYID)
            .entityname(UPDATED_ENTITYNAME)
            .entitytypeid(UPDATED_ENTITYTYPEID)
            .istopentity(UPDATED_ISTOPENTITY)
            .parententityid(UPDATED_PARENTENTITYID)
            .allowrating(UPDATED_ALLOWRATING)
            .allowreview(UPDATED_ALLOWREVIEW)
            .entitycreationdate(UPDATED_ENTITYCREATIONDATE)
            .entitybriefdescription(UPDATED_ENTITYBRIEFDESCRIPTION)
            .entityimage(UPDATED_ENTITYIMAGE)
            .entityimageContentType(UPDATED_ENTITYIMAGE_CONTENT_TYPE);
        StarkeEntityDTO starkeEntityDTO = starkeEntityMapper.toDto(updatedStarkeEntity);

        restStarkeEntityMockMvc.perform(put("/api/starke-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeEntityDTO)))
            .andExpect(status().isOk());

        // Validate the StarkeEntity in the database
        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeUpdate);
        StarkeEntity testStarkeEntity = starkeEntityList.get(starkeEntityList.size() - 1);
        assertThat(testStarkeEntity.getEntityid()).isEqualTo(UPDATED_ENTITYID);
        assertThat(testStarkeEntity.getEntityname()).isEqualTo(UPDATED_ENTITYNAME);
        assertThat(testStarkeEntity.getEntitytypeid()).isEqualTo(UPDATED_ENTITYTYPEID);
        assertThat(testStarkeEntity.getIstopentity()).isEqualTo(UPDATED_ISTOPENTITY);
        assertThat(testStarkeEntity.getParententityid()).isEqualTo(UPDATED_PARENTENTITYID);
        assertThat(testStarkeEntity.getAllowrating()).isEqualTo(UPDATED_ALLOWRATING);
        assertThat(testStarkeEntity.getAllowreview()).isEqualTo(UPDATED_ALLOWREVIEW);
        assertThat(testStarkeEntity.getEntitycreationdate()).isEqualTo(UPDATED_ENTITYCREATIONDATE);
        assertThat(testStarkeEntity.getEntitybriefdescription()).isEqualTo(UPDATED_ENTITYBRIEFDESCRIPTION);
        assertThat(testStarkeEntity.getEntityimage()).isEqualTo(UPDATED_ENTITYIMAGE);
        assertThat(testStarkeEntity.getEntityimageContentType()).isEqualTo(UPDATED_ENTITYIMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingStarkeEntity() throws Exception {
        int databaseSizeBeforeUpdate = starkeEntityRepository.findAll().size();

        // Create the StarkeEntity
        StarkeEntityDTO starkeEntityDTO = starkeEntityMapper.toDto(starkeEntity);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStarkeEntityMockMvc.perform(put("/api/starke-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeEntityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StarkeEntity in the database
        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStarkeEntity() throws Exception {
        // Initialize the database
        starkeEntityRepository.saveAndFlush(starkeEntity);

        int databaseSizeBeforeDelete = starkeEntityRepository.findAll().size();

        // Get the starkeEntity
        restStarkeEntityMockMvc.perform(delete("/api/starke-entities/{id}", starkeEntity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StarkeEntity> starkeEntityList = starkeEntityRepository.findAll();
        assertThat(starkeEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StarkeEntity.class);
        StarkeEntity starkeEntity1 = new StarkeEntity();
        starkeEntity1.setId(1L);
        StarkeEntity starkeEntity2 = new StarkeEntity();
        starkeEntity2.setId(starkeEntity1.getId());
        assertThat(starkeEntity1).isEqualTo(starkeEntity2);
        starkeEntity2.setId(2L);
        assertThat(starkeEntity1).isNotEqualTo(starkeEntity2);
        starkeEntity1.setId(null);
        assertThat(starkeEntity1).isNotEqualTo(starkeEntity2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StarkeEntityDTO.class);
        StarkeEntityDTO starkeEntityDTO1 = new StarkeEntityDTO();
        starkeEntityDTO1.setId(1L);
        StarkeEntityDTO starkeEntityDTO2 = new StarkeEntityDTO();
        assertThat(starkeEntityDTO1).isNotEqualTo(starkeEntityDTO2);
        starkeEntityDTO2.setId(starkeEntityDTO1.getId());
        assertThat(starkeEntityDTO1).isEqualTo(starkeEntityDTO2);
        starkeEntityDTO2.setId(2L);
        assertThat(starkeEntityDTO1).isNotEqualTo(starkeEntityDTO2);
        starkeEntityDTO1.setId(null);
        assertThat(starkeEntityDTO1).isNotEqualTo(starkeEntityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(starkeEntityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(starkeEntityMapper.fromId(null)).isNull();
    }
}
