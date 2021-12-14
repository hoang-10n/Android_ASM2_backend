package com.android.asm2.controller;

import com.android.asm2.model.Report;
import com.android.asm2.service.ReportService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    @RequestMapping(path = "/api/reports", method = RequestMethod.POST)
    public boolean addReport(@RequestBody Report report) {
        return reportService.addReport(report);
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

    @RequestMapping(path = "/init/reports", method = RequestMethod.GET)
    public void initReportDB() throws IOException {
        String sURL = "https://my-json-server.typicode.com/hoang-10n/Android_ASM2/reports";
        URL reportDB = new URL(sURL);

        StringBuilder sb = new StringBuilder();
        String line;

        HttpURLConnection connection = (HttpURLConnection) reportDB.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = bf.readLine()) != null) {
            sb.append(line);
        }

        reportService.deleteAll();

        try {
            JSONArray array = JSONFactoryUtil.createJSONArray(sb.toString());
            for (int i = 0; i < array.length(); i++) {
                reportService.saveReport(new Gson().fromJson(array.getJSONObject(i).toString(), Report.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
