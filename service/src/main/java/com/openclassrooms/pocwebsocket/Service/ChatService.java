package com.openclassrooms.pocwebsocket.Service;

import com.openclassrooms.pocwebsocket.Model.Message;
import com.openclassrooms.pocwebsocket.Model.Reclamation;
import com.openclassrooms.pocwebsocket.Repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final MessageRepository messageRepository;

    public void saveMessage (Message message) {
        messageRepository.save(message);
    }

    public List<Message> findAllByReclamation (Reclamation reclamation) {
        return messageRepository.findAllByReclamation(reclamation);
    }
}
