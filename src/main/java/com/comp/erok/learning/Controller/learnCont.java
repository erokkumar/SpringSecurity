package com.comp.erok.learning.Controller;

import com.comp.erok.learning.Model.User;
import com.comp.erok.learning.Model.learnmodel;
import com.comp.erok.learning.services.learnService;
import com.comp.erok.learning.services.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lear")
public class learnCont {

    @Autowired
    private learnService learnService;

    @Autowired
    private userService userService;

    @PostMapping
    public ResponseEntity<learnmodel> addLearnData(@RequestBody learnmodel data) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            learnService.saveData(data, userName);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAlldata() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.findByUserName(userName);
            List<learnmodel> alldata = user.getSubtopics();

            if (alldata.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(alldata, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Exception logging
            return new ResponseEntity<>("Error fetching data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("myid/{id}")
    public ResponseEntity<learnmodel> getdata(@PathVariable ObjectId id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.findByUserName(userName);

            Optional<learnmodel> dataOpt = user.getSubtopics().stream()
                    .filter(x -> x.getId().equals(id))
                    .findFirst();
            if(dataOpt.isPresent()){
                Optional<learnmodel> data = learnService.findById(id);
                if (data.isPresent()){
                    return new ResponseEntity<>(data.get(), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace(); // Exception logging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<learnmodel> updateLearndata(@PathVariable ObjectId id,
                                                      @RequestBody learnmodel data) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.findByUserName(userName);

            Optional<learnmodel> existingDataOpt = user.getSubtopics().stream()
                    .filter(x -> x.getId().equals(id))
                    .findFirst();

            if (existingDataOpt.isPresent()) {
                learnmodel existingData = existingDataOpt.get();
                if (!data.getTitle().isEmpty())
                    existingData.setTitle(data.getTitle());
                if (data.getContent() != null && !data.getContent().isEmpty())
                    existingData.setContent(data.getContent());

                learnService.saveData(existingData); // Save updated data
                return new ResponseEntity<>(existingData, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace(); // Exception logging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<learnmodel> removeLearndata(@PathVariable ObjectId id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            Optional<learnmodel> datOpt = userService.findByUserName(userName).getSubtopics().stream()
                    .filter(x -> x.getId().equals(id))
                    .findFirst();

            if (datOpt.isPresent()) {
                learnService.deleteByid(id, userName);
                return new ResponseEntity<>(datOpt.get(), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace(); // Exception logging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
