package com.yemreu.main.controller;


import com.yemreu.main.dto.ReportCreationDto;
import com.yemreu.main.dto.ReportUpdateDto;
import com.yemreu.main.model.Report;
import com.yemreu.main.service.ReportService;
import com.yemreu.main.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/report")
public class ReportController {

    private final UserService userService;
    private final ReportService reportService;

    public ReportController(UserService userService, ReportService reportService) {
        this.userService = userService;
        this.reportService = reportService;
    }

    @GetMapping("/create")
    public String displayReportCreationForm(Model model) {
        model.addAttribute("laboratories", userService.getLaboratories());
        model.addAttribute("patients", userService.getAllUsers());
        model.addAttribute("report", new ReportCreationDto());
        return "report/create_new_report";
    }

    @PostMapping("/create")
    public String createReport(@ModelAttribute("report") @Valid ReportCreationDto reportCreationDto,
                               final BindingResult result,
                               @RequestParam(value = "photo", required = false) MultipartFile multipartFile,
                               Model model) {
        if(result.hasErrors()) {
            model.addAttribute("laboratories", userService.getLaboratories());
            model.addAttribute("patients", userService.getAllUsers());
            return "report/create_new_report";
        }
        reportService.save(reportCreationDto, multipartFile);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public ModelAndView displayReportDetails(@PathVariable("id") Long id) {
        Report theReport = reportService.getOneReportById(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("report/report_details");
        mav.addObject("report", theReport);
        return mav;
    }

    // Already set in config class.
    //@PreAuthorize("hasRole('LABORATORY')")
    @GetMapping("/{id}/edit")
    public String displayReportEditForm(@PathVariable("id") Long id, Model model) {
        Report report = reportService.getOneReportById(id);
        model.addAttribute("report", report);
        model.addAttribute("laboratories", userService.getLaboratories());
        model.addAttribute("patients", userService.getAllUsers());
        return "report/report_edit";
    }
    @PostMapping("/{id}/edit")
    public String editReport(@ModelAttribute("report") @Valid ReportUpdateDto updateDto,
                             final BindingResult result,
                             Model model) {
        if(result.hasErrors()) {
            model.addAttribute("laboratories", userService.getLaboratories());
            model.addAttribute("patients", userService.getAllUsers());
            return "report/report_edit";
        }
        reportService.edit(updateDto);
        return "redirect:/report/" + updateDto.getId();
    }

    // Already set in config class.
    //@PreAuthorize("hasAnyRole('LABORATORY','ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteReport(@PathVariable("id") Long id) {
        reportService.deleteReportById(id);
        return "redirect:/";
    }

}
