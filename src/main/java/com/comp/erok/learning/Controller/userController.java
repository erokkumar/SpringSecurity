package com.comp.erok.learning.Controller;


import com.comp.erok.learning.Model.User;
import com.comp.erok.learning.Repository.userRepository;
import com.comp.erok.learning.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService UserService;

    @Autowired
    private userRepository userRepository;

    @GetMapping
    public List<User> findAllUser(){
        return UserService.getallUser();
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable ObjectId id){
//        User user = userService.getUserById(id).orElse(null);
//        if (user != null) {
//            return userService.getUserById(id);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = UserService.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            UserService.saveNewUser(userInDb);
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserbyId(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
