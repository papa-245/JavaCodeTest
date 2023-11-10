package com.assignment.util;

import com.assignment.model.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("forexView")
public class JasperReportView extends AbstractView {

    private JasperReport studentReport;
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //load file and compile it
        List<Student> studentList = (List<Student>) model.get("studentList");
       // String reportFormat = (String) model.get("reportFormat");

        File file = ResourceUtils.getFile("classpath:studentReport.jrxml");
        studentReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(studentList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Code Test");
        JasperPrint jasperPrint = JasperFillManager.fillReport(studentReport, parameters, dataSource);


//        if (reportFormat.equalsIgnoreCase("html")) {
            HtmlExporter htmlExporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
            htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
            htmlExporter.exportReport();

            JasperExportManager.exportReportToHtmlFile(jasperPrint, "\\studentReport.html");
  /*      }
        if (reportFormat.equalsIgnoreCase("pdf")) {*/
            JRPdfExporter exporter = new JRPdfExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("studentReport.pdf"));

            SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
            reportConfig.setSizePageToContent(true);
            reportConfig.setForceLineBreakPolicy(false);

            SimplePdfExporterConfiguration exportConfig  = new SimplePdfExporterConfiguration();
           // exportConfig.setMetadataAuthor("");
            exportConfig.setEncrypted(true);
            exportConfig.setAllowedPermissionsHint("PRINTING");

            exporter.setConfiguration(reportConfig);
            exporter.setConfiguration(exportConfig);
            exporter.exportReport();
            JasperExportManager.exportReportToPdfFile(jasperPrint, "\\studentReport.pdf");
        //}

    }

}
