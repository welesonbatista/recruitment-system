package br.com.weleson.recruitment_system.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.weleson.recruitment_system.exceptions.UserFoundException;
import br.com.weleson.recruitment_system.modules.company.entities.CompanyEntity;
import br.com.weleson.recruitment_system.modules.company.useCases.CreateCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/company")
@Tag(name = "Company", description = "Company management endpoints")
public class CompanyContoller {

  @Autowired
  private CreateCompanyUseCase createCompanyUseCase;

  @PostMapping("/")
  @Operation(summary = "Create Company", description = "Create a new company account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CompanyEntity.class))),
      @ApiResponse(responseCode = "400", description = "User already exists") })
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {

    try {
      var result = this.createCompanyUseCase.execute(companyEntity);
      return ResponseEntity.ok(result);
    } catch (UserFoundException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }
}
