package com.android.asm2.service;

import com.android.asm2.model.Zone;
import com.android.asm2.repo.ZoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ZoneService {
    @Autowired
    private ZoneRepo zoneRepo;

    public void saveZone(Zone zone) {
        zoneRepo.save(zone);
    }

    public List<Zone> getAllZones() {
        return zoneRepo.findAll();
    }

    public Zone getZoneById(String id) {
        Optional<Zone> zoneOptional = zoneRepo.findById(id);
        return zoneOptional.orElse(null);
    }

    public boolean addZone(Zone zone) {
        if (getZoneById(zone.getId()) == null) {
            saveZone(zone);
            return true;
        }
        return false;
    }

    public boolean updateZone(Zone zone) {
        if (getZoneById(zone.getId()) != null) {
            saveZone(zone);
            return true;
        }
        return false;
    }

    public Zone deleteZoneById(String id) {
        Zone zone = getZoneById(id);
        zoneRepo.delete(zone);
        return zone;
    }

    public void deleteAll() {
        zoneRepo.deleteAll();
    }
}
