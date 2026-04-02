package br.com.weleson.recruitment_system.modules.company.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.weleson.recruitment_system.modules.company.dto.CreateJobDTO;
import br.com.weleson.recruitment_system.modules.company.entities.CompanyEntity;
import br.com.weleson.recruitment_system.modules.company.repositories.CompanyRepository;
import br.com.weleson.recruitment_system.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private CompanyRepository companyRepository;

  @Before
  public void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
  }

  @Test
  public void should_be_able_to_create_a_new_job() throws Exception {

    var company = CompanyEntity.builder()
        .name("COMPANY_NAME_TEST")
        .email("contact_email_test@example.com")
        .username("COMPANY_USERNAME_TEST")
        .password("1234567890")
        .website("COMPANY_WEBSITE_TEST")
        .description("COMPANY_DESCRIPTION_TEST")
        .build();

    company = companyRepository.saveAndFlush(company);

    var createdJobDTO = CreateJobDTO.builder()
        .benefits("BENEFITS_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

    var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtils.objectToJson(createdJobDTO))
        .header("Authorization", TestUtils.generateToken(company.getId(), "JOBSJAVA_@123#")))
        .andExpect(MockMvcResultMatchers.status().isOk());

    System.out.println(result);
  }

  @Test
  public void should_not_be_able_to_create_a_new_job_if_company_is_not_found() throws Exception {
    var createdJobDTO = CreateJobDTO.builder()
        .benefits("BENEFITS_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

    mvc.perform(MockMvcRequestBuilders.post("/company/job/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtils.objectToJson(createdJobDTO))
        .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "JOBSJAVA_@123#")))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

  }
}