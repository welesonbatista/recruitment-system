package br.com.weleson.recruitment_system.modules.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.weleson.recruitment_system.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID>             {
  
}
