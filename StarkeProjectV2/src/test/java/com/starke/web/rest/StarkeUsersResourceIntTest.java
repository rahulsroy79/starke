package com.starke.web.rest;

import com.starke.Starkev2App;

import com.starke.domain.StarkeUsers;
import com.starke.repository.StarkeUsersRepository;
import com.starke.service.StarkeUsersService;
import com.starke.service.dto.StarkeUsersDTO;
import com.starke.service.mapper.StarkeUsersMapper;
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
 * Test class for the StarkeUsersResource REST controller.
 *
 * @see StarkeUsersResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starkev2App.class)
public class StarkeUsersResourceIntTest {

    private static final Integer DEFAULT_USERID = 1;
    private static final Integer UPDATED_USERID = 2;

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_USEREMAIL = "AAAAAAAAAA";
    private static final String UPDATED_USEREMAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_ISACTIVE = 1;
    private static final Integer UPDATED_ISACTIVE = 2;

    private static final Integer DEFAULT_USERTYPEID = 1;
    private static final Integer UPDATED_USERTYPEID = 2;

    private static final LocalDate DEFAULT_ACTIVESINCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTIVESINCE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_REGISTRATIONID = 1;
    private static final Integer UPDATED_REGISTRATIONID = 2;

    @Autowired
    private StarkeUsersRepository starkeUsersRepository;


    @Autowired
    private StarkeUsersMapper starkeUsersMapper;
    

    @Autowired
    private StarkeUsersService starkeUsersService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStarkeUsersMockMvc;

    private StarkeUsers starkeUsers;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StarkeUsersResource starkeUsersResource = new StarkeUsersResource(starkeUsersService);
        this.restStarkeUsersMockMvc = MockMvcBuilders.standaloneSetup(starkeUsersResource)
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
    public static StarkeUsers createEntity(EntityManager em) {
        StarkeUsers starkeUsers = new StarkeUsers()
            .userid(DEFAULT_USERID)
            .username(DEFAULT_USERNAME)
            .useremail(DEFAULT_USEREMAIL)
            .isactive(DEFAULT_ISACTIVE)
            .usertypeid(DEFAULT_USERTYPEID)
            .activesince(DEFAULT_ACTIVESINCE)
            .registrationid(DEFAULT_REGISTRATIONID);
        return starkeUsers;
    }

    @Before
    public void initTest() {
        starkeUsers = createEntity(em);
    }

    @Test
    @Transactional
    public void createStarkeUsers() throws Exception {
        int databaseSizeBeforeCreate = starkeUsersRepository.findAll().size();

        // Create the StarkeUsers
        StarkeUsersDTO starkeUsersDTO = starkeUsersMapper.toDto(starkeUsers);
        restStarkeUsersMockMvc.perform(post("/api/starke-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUsersDTO)))
            .andExpect(status().isCreated());

        // Validate the StarkeUsers in the database
        List<StarkeUsers> starkeUsersList = starkeUsersRepository.findAll();
        assertThat(starkeUsersList).hasSize(databaseSizeBeforeCreate + 1);
        StarkeUsers testStarkeUsers = starkeUsersList.get(starkeUsersList.size() - 1);
        assertThat(testStarkeUsers.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testStarkeUsers.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testStarkeUsers.getUseremail()).isEqualTo(DEFAULT_USEREMAIL);
        assertThat(testStarkeUsers.getIsactive()).isEqualTo(DEFAULT_ISACTIVE);
        assertThat(testStarkeUsers.getUsertypeid()).isEqualTo(DEFAULT_USERTYPEID);
        assertThat(testStarkeUsers.getActivesince()).isEqualTo(DEFAULT_ACTIVESINCE);
        assertThat(testStarkeUsers.getRegistrationid()).isEqualTo(DEFAULT_REGISTRATIONID);
    }

    @Test
    @Transactional
    public void createStarkeUsersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = starkeUsersRepository.findAll().size();

        // Create the StarkeUsers with an existing ID
        starkeUsers.setId(1L);
        StarkeUsersDTO starkeUsersDTO = starkeUsersMapper.toDto(starkeUsers);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStarkeUsersMockMvc.perform(post("/api/starke-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUsersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StarkeUsers in the database
        List<StarkeUsers> starkeUsersList = starkeUsersRepository.findAll();
        assertThat(starkeUsersList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUseridIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeUsersRepository.findAll().size();
        // set the field null
        starkeUsers.setUserid(null);

        // Create the StarkeUsers, which fails.
        StarkeUsersDTO starkeUsersDTO = starkeUsersMapper.toDto(starkeUsers);

        restStarkeUsersMockMvc.perform(post("/api/starke-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUsersDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeUsers> starkeUsersList = starkeUsersRepository.findAll();
        assertThat(starkeUsersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsernameIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeUsersRepository.findAll().size();
        // set the field null
        starkeUsers.setUsername(null);

        // Create the StarkeUsers, which fails.
        StarkeUsersDTO starkeUsersDTO = starkeUsersMapper.toDto(starkeUsers);

        restStarkeUsersMockMvc.perform(post("/api/starke-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUsersDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeUsers> starkeUsersList = starkeUsersRepository.findAll();
        assertThat(starkeUsersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUseremailIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeUsersRepository.findAll().size();
        // set the field null
        starkeUsers.setUseremail(null);

        // Create the StarkeUsers, which fails.
        StarkeUsersDTO starkeUsersDTO = starkeUsersMapper.toDto(starkeUsers);

        restStarkeUsersMockMvc.perform(post("/api/starke-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUsersDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeUsers> starkeUsersList = starkeUsersRepository.findAll();
        assertThat(starkeUsersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsertypeidIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeUsersRepository.findAll().size();
        // set the field null
        starkeUsers.setUsertypeid(null);

        // Create the StarkeUsers, which fails.
        StarkeUsersDTO starkeUsersDTO = starkeUsersMapper.toDto(starkeUsers);

        restStarkeUsersMockMvc.perform(post("/api/starke-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUsersDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeUsers> starkeUsersList = starkeUsersRepository.findAll();
        assertThat(starkeUsersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActivesinceIsRequired() throws Exception {
        int databaseSizeBeforeTest = starkeUsersRepository.findAll().size();
        // set the field null
        starkeUsers.setActivesince(null);

        // Create the StarkeUsers, which fails.
        StarkeUsersDTO starkeUsersDTO = starkeUsersMapper.toDto(starkeUsers);

        restStarkeUsersMockMvc.perform(post("/api/starke-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUsersDTO)))
            .andExpect(status().isBadRequest());

        List<StarkeUsers> starkeUsersList = starkeUsersRepository.findAll();
        assertThat(starkeUsersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStarkeUsers() throws Exception {
        // Initialize the database
        starkeUsersRepository.saveAndFlush(starkeUsers);

        // Get all the starkeUsersList
        restStarkeUsersMockMvc.perform(get("/api/starke-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(starkeUsers.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].useremail").value(hasItem(DEFAULT_USEREMAIL.toString())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE)))
            .andExpect(jsonPath("$.[*].usertypeid").value(hasItem(DEFAULT_USERTYPEID)))
            .andExpect(jsonPath("$.[*].activesince").value(hasItem(DEFAULT_ACTIVESINCE.toString())))
            .andExpect(jsonPath("$.[*].registrationid").value(hasItem(DEFAULT_REGISTRATIONID)));
    }
    

    @Test
    @Transactional
    public void getStarkeUsers() throws Exception {
        // Initialize the database
        starkeUsersRepository.saveAndFlush(starkeUsers);

        // Get the starkeUsers
        restStarkeUsersMockMvc.perform(get("/api/starke-users/{id}", starkeUsers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(starkeUsers.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.useremail").value(DEFAULT_USEREMAIL.toString()))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE))
            .andExpect(jsonPath("$.usertypeid").value(DEFAULT_USERTYPEID))
            .andExpect(jsonPath("$.activesince").value(DEFAULT_ACTIVESINCE.toString()))
            .andExpect(jsonPath("$.registrationid").value(DEFAULT_REGISTRATIONID));
    }
    @Test
    @Transactional
    public void getNonExistingStarkeUsers() throws Exception {
        // Get the starkeUsers
        restStarkeUsersMockMvc.perform(get("/api/starke-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStarkeUsers() throws Exception {
        // Initialize the database
        starkeUsersRepository.saveAndFlush(starkeUsers);

        int databaseSizeBeforeUpdate = starkeUsersRepository.findAll().size();

        // Update the starkeUsers
        StarkeUsers updatedStarkeUsers = starkeUsersRepository.findById(starkeUsers.getId()).get();
        // Disconnect from session so that the updates on updatedStarkeUsers are not directly saved in db
        em.detach(updatedStarkeUsers);
        updatedStarkeUsers
            .userid(UPDATED_USERID)
            .username(UPDATED_USERNAME)
            .useremail(UPDATED_USEREMAIL)
            .isactive(UPDATED_ISACTIVE)
            .usertypeid(UPDATED_USERTYPEID)
            .activesince(UPDATED_ACTIVESINCE)
            .registrationid(UPDATED_REGISTRATIONID);
        StarkeUsersDTO starkeUsersDTO = starkeUsersMapper.toDto(updatedStarkeUsers);

        restStarkeUsersMockMvc.perform(put("/api/starke-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUsersDTO)))
            .andExpect(status().isOk());

        // Validate the StarkeUsers in the database
        List<StarkeUsers> starkeUsersList = starkeUsersRepository.findAll();
        assertThat(starkeUsersList).hasSize(databaseSizeBeforeUpdate);
        StarkeUsers testStarkeUsers = starkeUsersList.get(starkeUsersList.size() - 1);
        assertThat(testStarkeUsers.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testStarkeUsers.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testStarkeUsers.getUseremail()).isEqualTo(UPDATED_USEREMAIL);
        assertThat(testStarkeUsers.getIsactive()).isEqualTo(UPDATED_ISACTIVE);
        assertThat(testStarkeUsers.getUsertypeid()).isEqualTo(UPDATED_USERTYPEID);
        assertThat(testStarkeUsers.getActivesince()).isEqualTo(UPDATED_ACTIVESINCE);
        assertThat(testStarkeUsers.getRegistrationid()).isEqualTo(UPDATED_REGISTRATIONID);
    }

    @Test
    @Transactional
    public void updateNonExistingStarkeUsers() throws Exception {
        int databaseSizeBeforeUpdate = starkeUsersRepository.findAll().size();

        // Create the StarkeUsers
        StarkeUsersDTO starkeUsersDTO = starkeUsersMapper.toDto(starkeUsers);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStarkeUsersMockMvc.perform(put("/api/starke-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(starkeUsersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StarkeUsers in the database
        List<StarkeUsers> starkeUsersList = starkeUsersRepository.findAll();
        assertThat(starkeUsersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStarkeUsers() throws Exception {
        // Initialize the database
        starkeUsersRepository.saveAndFlush(starkeUsers);

        int databaseSizeBeforeDelete = starkeUsersRepository.findAll().size();

        // Get the starkeUsers
        restStarkeUsersMockMvc.perform(delete("/api/starke-users/{id}", starkeUsers.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StarkeUsers> starkeUsersList = starkeUsersRepository.findAll();
        assertThat(starkeUsersList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StarkeUsers.class);
        StarkeUsers starkeUsers1 = new StarkeUsers();
        starkeUsers1.setId(1L);
        StarkeUsers starkeUsers2 = new StarkeUsers();
        starkeUsers2.setId(starkeUsers1.getId());
        assertThat(starkeUsers1).isEqualTo(starkeUsers2);
        starkeUsers2.setId(2L);
        assertThat(starkeUsers1).isNotEqualTo(starkeUsers2);
        starkeUsers1.setId(null);
        assertThat(starkeUsers1).isNotEqualTo(starkeUsers2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StarkeUsersDTO.class);
        StarkeUsersDTO starkeUsersDTO1 = new StarkeUsersDTO();
        starkeUsersDTO1.setId(1L);
        StarkeUsersDTO starkeUsersDTO2 = new StarkeUsersDTO();
        assertThat(starkeUsersDTO1).isNotEqualTo(starkeUsersDTO2);
        starkeUsersDTO2.setId(starkeUsersDTO1.getId());
        assertThat(starkeUsersDTO1).isEqualTo(starkeUsersDTO2);
        starkeUsersDTO2.setId(2L);
        assertThat(starkeUsersDTO1).isNotEqualTo(starkeUsersDTO2);
        starkeUsersDTO1.setId(null);
        assertThat(starkeUsersDTO1).isNotEqualTo(starkeUsersDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(starkeUsersMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(starkeUsersMapper.fromId(null)).isNull();
    }
}
