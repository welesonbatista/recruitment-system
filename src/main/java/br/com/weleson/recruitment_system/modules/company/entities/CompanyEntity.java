package br.com.weleson.recruitment_system.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "company")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "Tech Corp", requiredMode = Schema.RequiredMode.REQUIRED)
  private String name;

  @NotBlank
  @Pattern(regexp = "\\S+", message = "the username must not contain whitespace")
  @Schema(example = "techcorp", requiredMode = Schema.RequiredMode.REQUIRED)
  private String username;

  @Email(message = "the email must be a valid email address")
  @Schema(example = "contact@techcorp.com", requiredMode = Schema.RequiredMode.REQUIRED)
  private String email;

  @Length(min = 10, max = 100, message = "the password must be between 10 and 100 characters")
  @Schema(example = "securepassword123", requiredMode = Schema.RequiredMode.REQUIRED)
  private String password;

  @Schema(example = "https://www.techcorp.com")
  private String website;

  @Schema(example = "Technology company specialized in software solutions")
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;
}