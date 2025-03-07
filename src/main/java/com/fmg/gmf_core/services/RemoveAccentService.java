package com.fmg.gmf_core.services;

import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
public class RemoveAccentService {
    public String removeAccent(String title){
        return Normalizer.normalize(title, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }
}
