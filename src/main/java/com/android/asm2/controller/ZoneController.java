package com.android.asm2.controller;

import com.android.asm2.model.Zone;
import com.android.asm2.service.ZoneService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ZoneController {
    @Autowired
    private ZoneService zoneService;

    @RequestMapping(path = "/api/zones", method = RequestMethod.GET)
    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    @RequestMapping(path = "/api/zones", method = RequestMethod.POST)
    public boolean addZone(@RequestBody Zone zone) {
        return zoneService.addZone(zone);
    }

    @RequestMapping(path = "/api/zones", method = RequestMethod.PUT)
    public boolean updateZone(@RequestBody Zone zone) {
        return zoneService.updateZone(zone);
    }

    @RequestMapping(path = "/api/zones/{zoneId}", method = RequestMethod.DELETE)
    public Zone deleteZone(@PathVariable String zoneId) {
        return zoneService.deleteZoneById(zoneId);
    }

    @RequestMapping(path = "/api/zones/{zoneId}", method = RequestMethod.GET)
    public Zone getZoneByZoneId(@PathVariable String zoneId) {
        return zoneService.getZoneById(zoneId);
    }

    @RequestMapping(path = "/init/zones", method = RequestMethod.GET)
    public void initZoneDB() throws IOException {
        String sURL = "https://my-json-server.typicode.com/hoang-10n/Android_ASM2/zones";
        URL zoneDB = new URL(sURL);

        StringBuilder sb = new StringBuilder();
        String line;

        HttpURLConnection connection = (HttpURLConnection) zoneDB.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }

        zoneService.deleteAll();

        try {
            JSONArray array = new JSONArray(sb.toString());
            for (int i = 0; i < array.length(); i++) {
                zoneService.saveZone(new Gson().fromJson(array.getJSONObject(i).toString(), Zone.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
