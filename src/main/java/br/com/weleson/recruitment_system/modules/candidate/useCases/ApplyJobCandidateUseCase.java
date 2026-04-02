package br.com.weleson.recruitment_system.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.weleson.recruitment_system.exceptions.JobNotFoundExeption;
import br.com.weleson.recruitment_system.exceptions.UserNotFoundExeption;
import br.com.weleson.recruitment_system.modules.candidate.CandidateRepository;
import br.com.weleson.recruitment_system.modules.candidate.entities.ApplyJobEntity;
import br.com.weleson.recruitment_system.modules.candidate.repository.ApplyJobRepository;
import br.com.weleson.recruitment_system.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private ApplyJobRepository applyJobRepository;

  public ApplyJobEntity execute(UUID idcandidate, UUID idjob) {

    this.candidateRepository.findById(idcandidate)
        .orElseThrow(() -> {
          throw new UserNotFoundExeption();
        });

    this.jobRepository.findById(idjob)
        .orElseThrow(() -> {
          throw new JobNotFoundExeption();
        });

    var applyJob = ApplyJobEntity.builder()
        .candidateId(idcandidate)
        .jobId(idjob)
        .build();

    applyJob = applyJobRepository.save(applyJob);

    return applyJob;
  }

}
