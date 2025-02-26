package com.fmg.gmf_core.helpers;

import com.fmg.gmf_core.daos.UserDao;
import com.fmg.gmf_core.exceptions.ResourceAlreadyExistException;
import com.fmg.gmf_core.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    private final UserDao userDao;

    public UserHelper(UserDao userDao) {
        this.userDao = userDao;
    }

    public void emailExist(String email){
        if (!userDao.emailExists(email)){
            throw new ResourceAlreadyExistException("Email : "+email+" n'existe pas");
        }
    }
}
