package com.android.asm2.controller;

import com.android.asm2.model.Zone;
import com.android.asm2.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
