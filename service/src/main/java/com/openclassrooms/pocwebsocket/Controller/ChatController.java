package com.openclassrooms.pocwebsocket.Controller;

import com.openclassrooms.pocwebsocket.DTO.MessageDTO;
import com.openclassrooms.pocwebsocket.Model.Message;
import com.openclassrooms.pocwebsocket.Model.Reclamation;
import com.openclassrooms.pocwebsocket.Model.User;
import com.openclassrooms.pocwebsocket.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final UserRepository userRepository;
    private final ReclamationRepository reclamationRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageRepository messageRepository;

    @MessageMapping("/reclamation/{id}")
    public void send(@DestinationVariable Long id,  MessageDTO message) {
        User user = userRepository.findById(message.getAuthor_id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Reclamation reclamation = reclamationRepository.findById(message.getReclamation_id())
                .orElseThrow(() -> new RuntimeException("Reclamation not found"));
        Message msg = new Message(user, message.getMessage(), reclamation);

        messageRepository.save(msg);

        messagingTemplate.convertAndSend("/topic/reclamation/" + id, msg);
    }

    @GetMapping("/reclamation/{id}/messages")
    public List<Message> getAllMessagesByReclamation (@PathVariable Integer id) {
        Reclamation reclamation = reclamationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reclamation not found"));
        return messageRepository.findAllByReclamation(reclamation);
    }

}
