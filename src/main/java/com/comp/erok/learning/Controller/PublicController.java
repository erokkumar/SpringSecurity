package com.comp.erok.learning.Controller;


import com.comp.erok.learning.Model.User;
import com.comp.erok.learning.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private userService UserService;

    @PostMapping("/createuser")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        UserService.saveNewUser(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
