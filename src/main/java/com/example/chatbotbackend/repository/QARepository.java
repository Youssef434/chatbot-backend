package com.example.chatbotbackend.repository;

import com.example.chatbotbackend.model.QA;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QARepository extends MongoRepository<QA, Integer> {

}
