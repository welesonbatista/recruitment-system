package br.com.weleson.recruitment_system.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.weleson.recruitment_system.exceptions.CompanyNotFoundException;
import br.com.weleson.recruitment_system.modules.company.entities.JobEntity;
import br.com.weleson.recruitment_system.modules.company.repositories.CompanyRepository;
import br.com.weleson.recruitment_system.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private CompanyRepository companyRepository;

  public JobEntity execute(JobEntity jobEntity) {
    companyRepository.findById(jobEntity.getCompanyId())
        .orElseThrow(() -> {
          throw new CompanyNotFoundException();
        });

    return this.jobRepository.save(jobEntity);
  }

}
