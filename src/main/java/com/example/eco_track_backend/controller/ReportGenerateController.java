package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.service.ReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@AllArgsConstructor
public class ReportGenerateController {

    private final ReportService reportService;

    @GetMapping("/report/{format}")
    public String reportGenarate(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReport(format);
    }
}
