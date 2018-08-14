package com.starke.web.rest;

import com.starke.Starkev2App;

import com.starke.domain.StarkeUserTypes;
import com.starke.repository.StarkeUserTypesRepository;
import com.starke.service.StarkeUserTypesService;
import com.starke.service.dto.StarkeUserTypesDTO;
import com.starke.service.mapper.StarkeUserTypesMapper;
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
 * Test class for the StarkeUserTypesResource REST controller.
 *
 * @see StarkeUserTypesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starkev2App.class)
public class StarkeUserTypesResourceIntTest {

    private static final Integer DEFAULT_USERTYPEID = 1;
    private static final Integer UPDATED_USERTYPEID = 2;

    private static final String DEFAULT_TYPENAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPENAME = "BBBBBBBBBB";

    private static final String DEFAULT_USERTYPEDESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_USERTYPEDESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private StarkeUserTypesRepository starkeUserTypesRepository;


    @Autowired
    private StarkeUserTypesMapper starkeUserTypesMapper;
    

    @Autowired
    private StarkeUserTypesService starkeUserTypesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStarkeUserTypesMockMvc;

    private StarkeUserTypes starkeUserTypes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StarkeUserTypesResource starkeUserTypesResource = new StarkeUserTypesResource(starkeUserTypesService);
        this.restStarkeUserTypesMockMvc = MockMvcBuilders.standaloneSetup(starkeUserTypesResource)
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
    public static StarkeUserTypes createEntity(EntityManager em) {
        StarkeUserTypes starkeUserTypes = new StarkeUserTypes()
            .usertypeid(DEFAULT_USERTYPEID)
            .typename(DEFAULT_TYPENAME)
            .usertypedescription(DEFAULT_USERTYPEDESCRIPTION);
        return starkeUserTypes;
    }

    @Before
    public void initTest() {
        starkeUserTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createStarkeUserTypes() throws Exception {
        int databaseSizeBeforeCreate = starkeUserTypesRepository.findAll().size();

        // Create the StarkeUserTypes
        StarkeUserTypesDTO starkeUserTypesDTO = starkeUserTypesMapper.toDto(starkeUserTypes);
        restStarkeUserTypesMockMvc.perform(post("/api/starke-user-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUserTypesDTO)))
            .andExpect(status().isCreated());

        // Validate the StarkeUserTypes in the database
        List<StarkeUserTypes> starkeUserTypesList = starkeUserTypesRepository.findAll();
        assertThat(starkeUserTypesList).hasSize(databaseSizeBeforeCreate + 1);
        StarkeUserTypes testStarkeUserTypes = starkeUserTypesList.get(starkeUserTypesList.size() - 1);
        assertThat(testStarkeUserTypes.getUsertypeid()).isEqualTo(DEFAULT_USERTYPEID);
        assertThat(testStarkeUserTypes.getTypename()).isEqualTo(DEFAULT_TYPENAME);
        assertThat(testStarkeUserTypes.getUsertypedescription()).isEqualTo(DEFAULT_USERTYPEDESCRIPTION);
    }

    @Test
    @Transactional
    public void createStarkeUserTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = starkeUserTypesRepository.findAll().size();

        // Create the StarkeUserTypes with an existing ID
        starkeUserTypes.setId(1L);
        StarkeUserTypesDTO starkeUserTypesDTO = starkeUserTypesMapper.toDto(starkeUserTypes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStarkeUserTypesMockMvc.perform(post("/api/starke-user-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUserTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StarkeUserTypes in the database
        List<StarkeUserTypes> starkeUserTypesList = starkeUserTypesRepository.findAll();
        assertThat(starkeUserTypesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUsertypeidIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeUserTypesRepository.findAll().size();
        // set the field null
        starkeUserTypes.setUsertypeid(null);

        // Create the StarkeUserTypes, which fails.
        StarkeUserTypesDTO starkeUserTypesDTO = starkeUserTypesMapper.toDto(starkeUserTypes);

        restStarkeUserTypesMockMvc.perform(post("/api/starke-user-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUserTypesDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeUserTypes> starkeUserTypesList = starkeUserTypesRepository.findAll();
        assertThat(starkeUserTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypenameIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeUserTypesRepository.findAll().size();
        // set the field null
        starkeUserTypes.setTypename(null);

        // Create the StarkeUserTypes, which fails.
        StarkeUserTypesDTO starkeUserTypesDTO = starkeUserTypesMapper.toDto(starkeUserTypes);

        restStarkeUserTypesMockMvc.perform(post("/api/starke-user-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUserTypesDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeUserTypes> starkeUserTypesList = starkeUserTypesRepository.findAll();
        assertThat(starkeUserTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStarkeUserTypes() throws Exception {
        // Initialize the database
        starkeUserTypesRepository.saveAndFlush(starkeUserTypes);

        // Get all the starkeUserTypesList
        restStarkeUserTypesMockMvc.perform(get("/api/starke-user-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(starkeUserTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].usertypeid").value(hasItem(DEFAULT_USERTYPEID)))
            .andExpect(jsonPath("$.[*].typename").value(hasItem(DEFAULT_TYPENAME.toString())))
            .andExpect(jsonPath("$.[*].usertypedescription").value(hasItem(DEFAULT_USERTYPEDESCRIPTION.toString())));
    }
    

    @Test
    @Transactional
    public void getStarkeUserTypes() throws Exception {
        // Initialize the database
        starkeUserTypesRepository.saveAndFlush(starkeUserTypes);

        // Get the starkeUserTypes
        restStarkeUserTypesMockMvc.perform(get("/api/starke-user-types/{id}", starkeUserTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(starkeUserTypes.getId().intValue()))
            .andExpect(jsonPath("$.usertypeid").value(DEFAULT_USERTYPEID))
            .andExpect(jsonPath("$.typename").value(DEFAULT_TYPENAME.toString()))
            .andExpect(jsonPath("$.usertypedescription").value(DEFAULT_USERTYPEDESCRIPTION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingStarkeUserTypes() throws Exception {
        // Get the starkeUserTypes
        restStarkeUserTypesMockMvc.perform(get("/api/starke-user-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStarkeUserTypes() throws Exception {
        // Initialize the database
        starkeUserTypesRepository.saveAndFlush(starkeUserTypes);

        int databaseSizeBeforeUpdate = starkeUserTypesRepository.findAll().size();

        // Update the starkeUserTypes
        StarkeUserTypes updatedStarkeUserTypes = starkeUserTypesRepository.findById(starkeUserTypes.getId()).get();
        // Disconnect from session so that the updates on updatedStarkeUserTypes are not directly saved in db
        em.detach(updatedStarkeUserTypes);
        updatedStarkeUserTypes
            .usertypeid(UPDATED_USERTYPEID)
            .typename(UPDATED_TYPENAME)
            .usertypedescription(UPDATED_USERTYPEDESCRIPTION);
        StarkeUserTypesDTO starkeUserTypesDTO = starkeUserTypesMapper.toDto(updatedStarkeUserTypes);

        restStarkeUserTypesMockMvc.perform(put("/api/starke-user-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUserTypesDTO)))
            .andExpect(status().isOk());

        // Validate the StarkeUserTypes in the database
        List<StarkeUserTypes> starkeUserTypesList = starkeUserTypesRepository.findAll();
        assertThat(starkeUserTypesList).hasSize(databaseSizeBeforeUpdate);
        StarkeUserTypes testStarkeUserTypes = starkeUserTypesList.get(starkeUserTypesList.size() - 1);
        assertThat(testStarkeUserTypes.getUsertypeid()).isEqualTo(UPDATED_USERTYPEID);
        assertThat(testStarkeUserTypes.getTypename()).isEqualTo(UPDATED_TYPENAME);
        assertThat(testStarkeUserTypes.getUsertypedescription()).isEqualTo(UPDATED_USERTYPEDESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingStarkeUserTypes() throws Exception {
        int databaseSizeBeforeUpdate = starkeUserTypesRepository.findAll().size();

        // Create the StarkeUserTypes
        StarkeUserTypesDTO starkeUserTypesDTO = starkeUserTypesMapper.toDto(starkeUserTypes);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStarkeUserTypesMockMvc.perform(put("/api/starke-user-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUserTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StarkeUserTypes in the database
        List<StarkeUserTypes> starkeUserTypesList = starkeUserTypesRepository.findAll();
        assertThat(starkeUserTypesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStarkeUserTypes() throws Exception {
        // Initialize the database
        starkeUserTypesRepository.saveAndFlush(starkeUserTypes);

        int databaseSizeBeforeDelete = starkeUserTypesRepository.findAll().size();

        // Get the starkeUserTypes
        restStarkeUserTypesMockMvc.perform(delete("/api/starke-user-types/{id}", starkeUserTypes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StarkeUserTypes> starkeUserTypesList = starkeUserTypesRepository.findAll();
        assertThat(starkeUserTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StarkeUserTypes.class);
        StarkeUserTypes starkeUserTypes1 = new StarkeUserTypes();
        starkeUserTypes1.setId(1L);
        StarkeUserTypes starkeUserTypes2 = new StarkeUserTypes();
        starkeUserTypes2.setId(starkeUserTypes1.getId());
        assertThat(starkeUserTypes1).isEqualTo(starkeUserTypes2);
        starkeUserTypes2.setId(2L);
        assertThat(starkeUserTypes1).isNotEqualTo(starkeUserTypes2);
        starkeUserTypes1.setId(null);
        assertThat(starkeUserTypes1).isNotEqualTo(starkeUserTypes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StarkeUserTypesDTO.class);
        StarkeUserTypesDTO starkeUserTypesDTO1 = new StarkeUserTypesDTO();
        starkeUserTypesDTO1.setId(1L);
        StarkeUserTypesDTO starkeUserTypesDTO2 = new StarkeUserTypesDTO();
        assertThat(starkeUserTypesDTO1).isNotEqualTo(starkeUserTypesDTO2);
        starkeUserTypesDTO2.setId(starkeUserTypesDTO1.getId());
        assertThat(starkeUserTypesDTO1).isEqualTo(starkeUserTypesDTO2);
        starkeUserTypesDTO2.setId(2L);
        assertThat(starkeUserTypesDTO1).isNotEqualTo(starkeUserTypesDTO2);
        starkeUserTypesDTO1.setId(null);
        assertThat(starkeUserTypesDTO1).isNotEqualTo(starkeUserTypesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(starkeUserTypesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(starkeUserTypesMapper.fromId(null)).isNull();
    }
}
