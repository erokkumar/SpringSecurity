package com.comp.erok.learning.Model;

import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import java.time.LocalDateTime;

@Data
@Document(collection = "learn_data")
@NoArgsConstructor
public class learnmodel {
    @Id
    private ObjectId id;

    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
}
