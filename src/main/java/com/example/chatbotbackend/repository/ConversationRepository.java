package com.example.chatbotbackend.repository;

import com.example.chatbotbackend.model.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepository extends MongoRepository<Conversation, Integer> {

}
