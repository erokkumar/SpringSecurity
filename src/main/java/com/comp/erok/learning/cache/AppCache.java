package com.comp.erok.learning.cache;

import com.comp.erok.learning.Model.cacheModel;
import com.comp.erok.learning.Repository.cacheRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private cacheRepo cacheRepository;

    // initialize empty map
    public Map<String, String> cacheMap = new HashMap<>();

    @PostConstruct
    public void init() {
        List<cacheModel> all = cacheRepository.findAll();

        for (cacheModel item : all) {
            cacheMap.put(item.getKey(), item.getValue());
        }

        // DO NOT set cacheMap = null
    }
}
