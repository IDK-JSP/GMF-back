package com.fmg.gmf_core.helpers;

import com.fmg.gmf_core.daos.MeasurementDao;
import com.fmg.gmf_core.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MeasurementHelper {
    private final MeasurementDao measurementDao;

    public MeasurementHelper(MeasurementDao measurementDao) {
        this.measurementDao = measurementDao;
    }
    public void measurementExist(int id){
        if (!measurementDao.measurementExist(id)){
            throw new ResourceNotFoundException("Mesure "+id+" innexistant");
        }
    }
}
