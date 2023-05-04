package com.ramenshop.server.repository;

import com.ramenshop.server.model.Ramen;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RamenRepository extends MongoRepository<Ramen, String> {
}
