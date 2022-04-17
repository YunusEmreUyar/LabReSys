package com.yemreu.main.service.impl;

import com.yemreu.main.dto.ReportCreationDto;
import com.yemreu.main.dto.ReportUpdateDto;
import com.yemreu.main.exception.ReportNotFoundException;
import com.yemreu.main.model.Report;
import com.yemreu.main.repository.ReportRepository;
import com.yemreu.main.service.ReportService;
import com.yemreu.main.service.UserService;
import com.yemreu.main.util.FileUploadUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserService userService;

    public ReportServiceImpl(ReportRepository reportRepository, UserService userService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
    }

    @Override
    public List<Report> getAll(String contains, Sort sort) {
        if (contains != null) {
            return reportRepository.sortAndFilterByContainingString(contains, sort);
        }
        return reportRepository.findAll();
    }

    @Override
    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    @Override
    public void deleteReportById(Long id) {
        reportRepository.deleteById(id);
    }

    @Override
    public Report getOneReportById(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isPresent()) {
            return report.get();
        }
        throw new ReportNotFoundException("The Report that you are looking for is not found.");
    }

    @Override
    public List<Report> sortAndFilter(String ordering, String contains) {
        Sort sort = ordering.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(Sort.Direction.ASC, "date") : Sort.by(Sort.Direction.DESC, "date");
        return getAll(contains, sort);
    }

    @Override
    public void edit(ReportUpdateDto updateDto) {
        Report toUpdate = reportRepository.getById(updateDto.getId());
        toUpdate.setDiagnosisDetails(updateDto.getDiagnosisDetails());
        toUpdate.setDiagnosisTitle(updateDto.getDiagnosisTitle());
        toUpdate.setLaboratory(userService.getOneUserById(updateDto.getLaboratory()));
        toUpdate.setPatient(userService.getOneUserById(updateDto.getPatient()));
        reportRepository.save(toUpdate);
    }

    @Override
    public Report save(ReportCreationDto reportCreationDto, MultipartFile multipartFile) {
        String fileName = multipartFile.isEmpty() ? null : StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Report report = new Report(
                fileName,
                userService.getOneUserById(reportCreationDto.getPatient()),
                userService.getOneUserById(reportCreationDto.getLaboratory()),
                reportCreationDto.getDiagnosisTitle(),
                reportCreationDto.getDiagnosisDetails()
        );
        Report created = reportRepository.save(report);
        if (created.getPhoto() != null) {
            checkMultipartFile(multipartFile, created.getId(), fileName);
        }
        return created;
    }

    private void checkMultipartFile(MultipartFile multipartFile, Long reportId, String fileName) {
        Path uploadDirection = Paths.get("report-images").resolve(Long.toString(reportId));
        try {
            FileUploadUtil.saveFile(uploadDirection, fileName, multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
