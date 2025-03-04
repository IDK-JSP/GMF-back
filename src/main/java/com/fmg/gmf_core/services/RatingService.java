package com.fmg.gmf_core.services;

import org.springframework.stereotype.Service;

@Service
public class RatingService {
    public Double rate(Double currentRate, int nb_rate, int userRate){
        if (nb_rate == 0){
            return (double) userRate;
        }
        return ((currentRate * nb_rate) + userRate) / (nb_rate + 1);
    }
}
