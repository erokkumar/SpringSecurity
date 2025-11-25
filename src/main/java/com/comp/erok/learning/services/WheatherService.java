package com.comp.erok.learning.services;

import com.comp.erok.learning.api.response.WeatherResponse;
import com.comp.erok.learning.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WheatherService {

    @Value("${weatherstack.api.key}")
    private String KEY;

    @Value("${weatherstack.api.url}")   // ✔ Matches your application.properties
    private String BASE_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWather(String cityName) {

        // Step 1: Check if cache ha value
        String cacheUrl = appCache.cacheMap.get("Weather_api");
        String finalUrl;
        // String finalUrl = appCache.cacheMap.get("Weather_api").replace("<city>", cityName).replace("<apikey>", KEY);

        if (cacheUrl != null) {
            // Dynamic URL from DB
            finalUrl = cacheUrl
                    .replace("<apikey>", KEY)
                    .replace("<city>", cityName);
        } else {
            // Fallback → properties file
            finalUrl = BASE_URL + "?access_key=" + KEY + "&query=" + cityName;
        }

        System.out.println("Final URL: " + finalUrl);

        ResponseEntity<WeatherResponse> response =
                restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);

        return response.getBody();
    }
}

