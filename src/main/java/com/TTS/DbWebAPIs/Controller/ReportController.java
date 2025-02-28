package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Report;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InternalServerException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.ReportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.TTS.DbWebAPIs.Service.ReportServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/report1")
public class ReportController {

    private  final ReportServiceInterface reportService;

    @Transactional
    @GetMapping("/dts")
    public ResponseEntity<?> getUserDTSReport(@RequestParam String username, @RequestParam String startDate, @RequestParam String endDate) throws IOException, DatabaseException, InternalServerException {

           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
           // Parse the string to LocalDate
           LocalDate strtDate = LocalDate.parse(startDate, formatter);
           LocalDate edDate = LocalDate.parse(endDate, formatter);
           List<Report> reports = reportService.getUserDTSReport(username,strtDate,edDate);

           HttpHeaders headers = new HttpHeaders();
           byte[] bytes = generateExcelReport(reports);
           headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  // For generic binary content
           headers.setContentDisposition(ContentDisposition.builder("attachment")
                   .filename("file.xlsx")
                   .build());
           return  new ResponseEntity<>(bytes, headers, HttpStatus.OK);

    }

    private byte[] generateExcelReport(List<Report> reports) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Daily Worksheet");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        // Create data cell style for proper alignment
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataCellStyle.setWrapText(true);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Date Of TimeShare", "Project Code", "Project Name", "Activity Name", "Task Name",
                "Start Time", "End Time", "Consumed Time", "Measurable Name", "Measurable Quantity",
                "Measurable Unit", "Description"};

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowNum = 1;
        for (Report report : reports) {
            Row row = sheet.createRow(rowNum++);
            System.out.println(report.getTimeShareDate());
            row.createCell(0).setCellValue(report.getTimeShareDate());
            System.out.println(report.getProjectCode());
            row.createCell(1).setCellValue(report.getProjectCode());
            System.out.println(report.getProjectName());
            row.createCell(2).setCellValue(report.getProjectName());
            System.out.println(report.getActivityName());
            row.createCell(3).setCellValue(report.getActivityName());
            System.out.println(report.getTaskName());
            row.createCell(4).setCellValue(report.getTaskName());
            System.out.println(report.getStartTime());
            row.createCell(5).setCellValue(report.getStartTime());
            System.out.println(report.getEndTime());
            row.createCell(6).setCellValue(report.getEndTime());
            System.out.println(report.getTimeDifference());
            row.createCell(7).setCellValue(report.getTimeDifference());
            System.out.println(report.getMeasurableName());
            row.createCell(8).setCellValue(report.getMeasurableName());
            System.out.println(report.getMeasurableQty());
            row.createCell(9).setCellValue(report.getMeasurableQty());
            System.out.println(report.getMeasurableUnit());
            row.createCell(10).setCellValue(report.getMeasurableUnit());
            System.out.println(report.getDescription());
            row.createCell(11).setCellValue(report.getDescription());

            for (int i = 0; i < headers.length; i++) {
                row.getCell(i).setCellStyle(dataCellStyle);
            }
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        // Adjust column widths slightly for better readability
        for (int i = 0; i < headers.length; i++) {
            int currentWidth = sheet.getColumnWidth(i);
            sheet.setColumnWidth(i, currentWidth + 1000); // Adds slight padding
        }

        // Write the workbook to a ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();
        return byteArrayOutputStream.toByteArray();
    }
}
