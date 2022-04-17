package com.yemreu.main.controller;

import com.yemreu.main.model.Report;
import com.yemreu.main.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/")
public class StaticPageController {

    private final ReportService reportService;

    public StaticPageController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public String home(Model model) {
        return sortReports("asc", "", model);
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/sort-and-filter")
    public String sortReports(@RequestParam(value = "ordering", required = false) String ordering, @RequestParam("contains") String contains, Model model) {
        if (ordering == null) {
            ordering = "asc";
        }
        List<Report> reports = reportService.sortAndFilter(ordering, contains);
        model.addAttribute("contains", contains);
        model.addAttribute("ordering", ordering);
        model.addAttribute("reverseOrdering", ordering.equals("asc")?"desc":"asc");
        model.addAttribute("reports", reports);
        return "index";
    }



}
