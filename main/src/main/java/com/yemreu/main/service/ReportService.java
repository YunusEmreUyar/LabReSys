package com.yemreu.main.service;

import com.yemreu.main.dto.ReportCreationDto;
import com.yemreu.main.dto.ReportUpdateDto;
import com.yemreu.main.model.Report;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ReportService {
    Report save(ReportCreationDto reportCreationDto, MultipartFile multipartFile);
    List<Report> getAll(String contains, Sort sort);
    List<Report> getAll();
    void deleteReportById(Long id);
    Report getOneReportById(Long id);
    List<Report> sortAndFilter(String ordering, String contains);

    void edit(ReportUpdateDto reportUpdateDto);
}
