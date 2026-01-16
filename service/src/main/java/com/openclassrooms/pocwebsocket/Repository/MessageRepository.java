package com.openclassrooms.pocwebsocket.Repository;

import com.openclassrooms.pocwebsocket.Model.Message;
import com.openclassrooms.pocwebsocket.Model.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByReclamation(Reclamation reclamation);
}
