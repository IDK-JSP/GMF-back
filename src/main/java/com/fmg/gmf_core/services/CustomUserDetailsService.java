package com.fmg.gmf_core.services;


import com.fmg.gmf_core.daos.UserDao;
import com.fmg.gmf_core.entitys.CustomUserDetails;
import com.fmg.gmf_core.entitys.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);
        return new CustomUserDetails(user);
    }
}
