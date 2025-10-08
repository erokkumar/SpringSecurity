package com.comp.erok.learning.Controller;


import com.comp.erok.learning.Model.User;
import com.comp.erok.learning.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private userService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getallUser();
        if(users != null && !users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-Admin")
    public ResponseEntity<?> createAdminUser(@RequestBody User user){
        userService.saveAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
