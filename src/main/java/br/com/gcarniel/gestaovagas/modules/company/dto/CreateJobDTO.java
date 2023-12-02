package br.com.gcarniel.gestaovagas.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {
    private String description;
    private String level;
    private String benefits;

}
