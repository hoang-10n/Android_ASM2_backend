package com.android.asm2.service;

import com.android.asm2.model.Report;
import com.android.asm2.repo.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ReportService {
    @Autowired
    private ReportRepo reportRepo;

    public void saveReport(Report report) {
        reportRepo.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepo.findAll();
    }

    public Report getReportByZoneId(String zoneId) {
        Optional<Report> reportOptional = reportRepo.findById(zoneId);
        return reportOptional.orElse(null);
    }

    public boolean addReport(Report report) {
        if (getReportByZoneId(report.getZoneId()) == null) {
            saveReport(report);
            return true;
        }
        return false;
    }

    public boolean updateReport(Report report) {
        if (getReportByZoneId(report.getZoneId()) != null) {
            saveReport(report);
            return true;
        }
        return false;
    }

    public Report deleteReportByZoneId(String zoneId) {
        Report report = getReportByZoneId(zoneId);
        reportRepo.delete(report);
        return report;
    }

    public void deleteAll() {
        reportRepo.deleteAll();
    }
}
