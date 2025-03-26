package com.fmg.gmf_core.services;

import org.springframework.stereotype.Service;

@Service
public class RatingService {
    public Double rate(Double currentRate, int nbRate, int userRate){
        if (nbRate == 0){
            return (double) userRate;
        }
        return ((currentRate * nbRate) + userRate) / (nbRate + 1);
    }
}
