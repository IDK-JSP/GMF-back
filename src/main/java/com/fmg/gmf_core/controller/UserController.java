package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.UserDao;
import com.fmg.gmf_core.entitys.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }
    @GetMapping("/search")
    public ResponseEntity<List<User>> getUserByEmail(@RequestParam String query){
        return  ResponseEntity.ok(Collections.singletonList(userDao.findByEmail(query)));
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userDao.findAll());
    }


}
