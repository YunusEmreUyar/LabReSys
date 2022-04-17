package com.yemreu.main.repository;

import com.yemreu.main.model.Report;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r from #{#entityName} r WHERE " +
            "CONCAT(r.patient.tc, ' ', r.patient.name, ' ', r.patient.surname, ' ', " +
            "r.laboratory.name, ' ', r.laboratory.surname) LIKE %?1%")
    List<Report> sortAndFilterByContainingString(String contains, Sort sort);
}
