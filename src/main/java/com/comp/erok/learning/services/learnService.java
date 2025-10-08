package com.comp.erok.learning.services;

import com.comp.erok.learning.Model.User;
import com.comp.erok.learning.Model.learnmodel;
import com.comp.erok.learning.Repository.learnRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class learnService {
    @Autowired
    private userService userService;
    @Autowired
    private learnRepository learnRepository;

    @Transactional
    public void saveData(learnmodel lear, String userName) {
        try {
            User user = userService.findByUserName(userName);
            lear.setDate(LocalDateTime.now());
            learnmodel saved = learnRepository.save(lear);
            user.getSubtopics().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Error saving data", e);
        }
    }

    public void saveData(learnmodel learnmodel) {
        learnRepository.save(learnmodel);
    }

    public List<learnmodel> getAllData() {
        return learnRepository.findAll();
    }

    public Optional<learnmodel> findById(ObjectId id){
        return learnRepository.findById(id);
    }

    public void deleteByid(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getSubtopics().removeIf(learnmodel -> learnmodel.getId().equals(id));
        userService.saveNewUser(user);
        learnRepository.deleteById(id);
    }



//    public Optional<learnmodel> findById(Long id) {
//        return learnRepository.findById(id);
//    }
//
//    public void deleteById(Long id) {
//        learnRepository.deleteById(id);
//    }
//
//    public void saved(learnmodel data, String userName) {
//        try {
//            User user = userService.findByUsername(userName);
//            data.setDate(LocalDateTime.now());
//            learnRepository.save(data);
//        } catch (Exception e) {
//            throw new RuntimeException("Error saving data", e);
//        }
//    }
//
//    public List<learnmodel> getLearnModelsByUserId(Long userId) {
//        return learnRepository.findByUserId(userId);
//    }
}

