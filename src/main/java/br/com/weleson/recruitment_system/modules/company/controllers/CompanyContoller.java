package br.com.weleson.recruitment_system.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.weleson.recruitment_system.exceptions.UserFoundException;
import br.com.weleson.recruitment_system.modules.company.entities.CompanyEntity;
import br.com.weleson.recruitment_system.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/company")
public class CompanyContoller {

  @Autowired
  private CreateCompanyUseCase createCompanyUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {

    try {
      var result = this.createCompanyUseCase.execute(companyEntity);
      return ResponseEntity.ok(result);
    } catch (UserFoundException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Internal Server Error");
    }

  }
}
