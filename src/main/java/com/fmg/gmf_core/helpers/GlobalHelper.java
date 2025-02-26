package com.fmg.gmf_core.helpers;

import com.fmg.gmf_core.exceptions.ResourceAlreadyExistException;
import com.fmg.gmf_core.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class GlobalHelper {
    public void isEmpty(List list){
        if (list.isEmpty()){
            throw new ResourceNotFoundException("Aucunne données disponible");
        }
    }

    public void exist(Boolean result, String dataName){
        if (result){
            throw new ResourceNotFoundException(dataName + " non existant(e)");
        }
    }

    public void notExist(Boolean result, String dataName){
        if (result){
            throw new ResourceAlreadyExistException(dataName+ " déjà existant(e).");

        }
    }
}
