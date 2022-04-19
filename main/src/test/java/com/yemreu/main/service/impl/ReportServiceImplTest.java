package com.yemreu.main.service.impl;

import com.yemreu.main.dto.UserRegisterDto;
import com.yemreu.main.model.Report;
import com.yemreu.main.model.User;
import com.yemreu.main.repository.ReportRepository;
import com.yemreu.main.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReportServiceImplTest {

    private ReportServiceImpl reportService;
    private ReportRepository reportRepository;
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        reportRepository = Mockito.mock(ReportRepository.class);
        userService = Mockito.mock(UserService.class);
        reportService = Mockito.spy(new ReportServiceImpl(reportRepository, userService));
    }

    @Test
    public void whenGetAllCalled_itShouldReturnAllReportsAsList() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setName("Yunus");
        dto.setPassword("passwd");
        dto.setUsername("YunusEmre");
        dto.setSurname("Uyar");
        dto.setTc("12345678910");
        dto.setRole("ROLE_USER");

        User user = userService.save(dto);

        Sort sort = Sort.by(Sort.Direction.ASC, "date");

        Report report = new Report(
                null,
                user,
                user,
                "Diag title",
                "Diag details."
        );
        Report report2 = new Report(
                null,
                user,
                user,
                "Diag title",
                "Diag details."
        );

        List<Report> expected = new ArrayList<>();
        expected.add(report);
        expected.add(report2);

        Mockito.when(reportService.getAll("asc", sort)).thenReturn(expected);
        Assert.assertEquals(expected.size(), 2);
        Mockito.verify(reportService).getAll("asc", sort);
    }

}