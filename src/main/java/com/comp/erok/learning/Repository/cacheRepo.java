package com.comp.erok.learning.Repository;

import com.comp.erok.learning.Model.cacheModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cacheRepo extends MongoRepository<cacheModel, ObjectId> {
}
