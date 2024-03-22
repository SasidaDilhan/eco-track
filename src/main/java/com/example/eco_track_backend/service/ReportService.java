package com.example.eco_track_backend.service;

import com.example.eco_track_backend.model.TruckDriver;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.TruckDriverRepository;
import com.example.eco_track_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class ReportService {

    private final TruckDriverRepository truckDriverRepository;
    private final UserRepository userRepository;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

        String path = "C:\\Users\\Bad-Boy\\OneDrive - Sri Lanka Institute of Information Technology\\Desktop\\reports";

        List<User> truckDriverList = userRepository.findAll();

        //load File
        File file = ResourceUtils.getFile("classpath:driver.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(truckDriverList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy","Eco-Track");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("html")){

            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\User.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){

            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\User.pdf");
        }

        return "Report Generated in path : "+path;
    }
}
