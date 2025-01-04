package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Report;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.ReportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.TTS.DbWebAPIs.Service.ReportServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private  final ReportServiceInterface reportService;

    @GetMapping("/dts")
    public ResponseEntity<?> getUserDTSReport(@RequestParam String username, @RequestParam String startDate, @RequestParam String endDate) throws IOException {
       try {
           List<Report> reports = reportService.getUserDTSReport(username,startDate,endDate);
           HttpHeaders headers = new HttpHeaders();
           byte[] bytes = generateExcelReport(reports);
           headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  // For generic binary content
           headers.setContentDisposition(ContentDisposition.builder("attachment")
                   .filename("file.xlsx")
                   .build());
           return  new ResponseEntity<>(bytes, headers, HttpStatus.OK);
       }catch (SQLException ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An error occurred while getting a dts report. Please try again later.",null));
       } catch (Exception ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
       }
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
            row.createCell(0).setCellValue(report.getTimeShareDate());
            row.createCell(1).setCellValue(report.getProjectCode());
            row.createCell(2).setCellValue(report.getProjectName());
            row.createCell(3).setCellValue(report.getActivityName());
            row.createCell(4).setCellValue(report.getTaskName());
            row.createCell(5).setCellValue(report.getStartTime());
            row.createCell(6).setCellValue(report.getEndTime());
            row.createCell(7).setCellValue(report.getConsumedTime());
            row.createCell(8).setCellValue(report.getMeasurableName());
            row.createCell(9).setCellValue(report.getMeasurableQty());
            row.createCell(10).setCellValue(report.getMeasurableUnit());
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
