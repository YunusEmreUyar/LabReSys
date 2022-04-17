package com.yemreu.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportCreationDto {

    private MultipartFile photo;
    // Patient and laboratory provided as Long data time because
    // Report creation form select options values going to be User's ids.
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