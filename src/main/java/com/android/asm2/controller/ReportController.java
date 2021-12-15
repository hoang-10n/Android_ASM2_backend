package com.android.asm2.controller;

import com.android.asm2.model.Report;
import com.android.asm2.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @RequestMapping(path = "/api/reports", method = RequestMethod.GET)
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @RequestMapping(path = "/api/reports", method = RequestMethod.DELETE)
    public void deleteAllReports() {
        reportService.deleteAll();
    }

    @RequestMapping(path = "/api/reports", method = RequestMethod.POST)
    public boolean addReport(@RequestBody Report report) {
        return reportService.addReport(report);
    }

    @RequestMapping(path = "/api/reports/many", method = RequestMethod.POST)
    public boolean addManyReports(@RequestBody Report[] reports) {
        try {
            for (Report report : reports) reportService.addReport(report);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping(path = "/api/reports", method = RequestMethod.PUT)
    public boolean updateReport(@RequestBody Report report) {
        return reportService.updateReport(report);
    }

    @RequestMapping(path = "/api/reports/{zoneId}", method = RequestMethod.DELETE)
    public Report deleteReport(@PathVariable String zoneId) {
        return reportService.deleteReportByZoneId(zoneId);
    }

    @RequestMapping(path = "/api/reports/{zoneId}", method = RequestMethod.GET)
    public Report getReportByZoneId(@PathVariable String zoneId) {
        return reportService.getReportByZoneId(zoneId);
    }
}
