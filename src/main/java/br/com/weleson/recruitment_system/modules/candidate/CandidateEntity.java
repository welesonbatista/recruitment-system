package br.com.weleson.recruitment_system.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@Data
@Entity(name = "candidates")
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;

  @NotBlank
  @Pattern(regexp = "\\S+", message = "the username must not contain whitespace")
  private String username;

  @Email(message = "the email must be a valid email address")
  private String email;

  @Length(min = 10, max = 100, message = "the password must be between 10 and 100 characters")
  private String password;

  private String description;
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
