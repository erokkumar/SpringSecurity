package com.comp.erok.learning.Model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_cache")
@Data
@NoArgsConstructor
public class cacheModel {
    private String Key;
    private String Value;
}
