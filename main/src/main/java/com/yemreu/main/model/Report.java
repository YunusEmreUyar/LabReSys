package com.yemreu.main.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Report {
    // Unique identifier of Report model.
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    // Stores photo's file location.
    @Column(nullable = true, length = 255)
    private String photo;

    // An report object is specific for patient so patient can not be null.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User patient;

    // Constituent of report model. Can not be null.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "laboratory_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User laboratory;

    // Creation time of report.
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;
    // Diagnosis title.
    private String diagnosisTitle;
    // Diagnosis details.
    @Column(length = 1000)
    private String diagnosisDetails;

    public Report(String photo, User patient, User laboratory, String diagnosisTitle, String diagnosisDetails) {
        this.photo = photo;
        this.patient = patient;
        this.laboratory = laboratory;
        this.diagnosisTitle = diagnosisTitle;
        this.diagnosisDetails = diagnosisDetails;
    }
    
    @Transient
    public String getPhotoPath() {
        if (photo == null || id == null) {
            return "report-images/defaultPhoto.jpg";
        }
        return "report-images/" + id + "/" + photo;
    }

}
