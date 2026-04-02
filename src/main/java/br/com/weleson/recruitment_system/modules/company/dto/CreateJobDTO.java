package br.com.weleson.recruitment_system.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {

  @Schema(example = "vacancies for java developer", requiredMode = Schema.RequiredMode.REQUIRED)
  private String description;

  @Schema(example = "health insurance, meal voucher", requiredMode = Schema.RequiredMode.REQUIRED)
  private String benefits;

  @Schema(example = "Junior, Pleno, Senior", requiredMode = Schema.RequiredMode.REQUIRED)
  private String level;
}