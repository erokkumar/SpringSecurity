package com.comp.erok.learning.Repository;

import com.comp.erok.learning.Model.User;
import com.comp.erok.learning.Model.learnmodel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface learnRepository extends MongoRepository<learnmodel, ObjectId> {
}
