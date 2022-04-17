package com.yemreu.main.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class ReportUpdateDto {

    @NotNull
    private Long id;
    @NotNull
    private Long patient;
    @NotNull
    private Long laboratory;
    @NotNull
    @Length(min = 5)
    private String diagnosisTitle;
    @NotNull
    @Length(min = 10)
    private String diagnosisDetails;
}
