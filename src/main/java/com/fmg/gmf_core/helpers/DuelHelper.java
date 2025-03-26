package com.fmg.gmf_core.helpers;

import com.fmg.gmf_core.daos.DuelDao;
import com.fmg.gmf_core.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DuelHelper {
    private final DuelDao duelDao;

    public DuelHelper(DuelDao duelDao) {
        this.duelDao = duelDao;
    }
    public void duelExist(int id){
        if (!duelDao.duelExist(id)){
            throw new ResourceNotFoundException("Duel "+id+" innexistant");
        }

    }
    public void recipeExistInDuel (int idDuel, int idRecipe){
        if (!duelDao.recipeExistInDuel(idDuel, idRecipe)){
            throw new ResourceNotFoundException("La recette " + idRecipe +" n'existe pas dans le duel " + idDuel);
        }
    }
}
