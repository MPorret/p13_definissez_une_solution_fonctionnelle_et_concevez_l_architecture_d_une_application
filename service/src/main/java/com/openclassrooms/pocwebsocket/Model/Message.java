package com.openclassrooms.pocwebsocket.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "MESSAGES")
@RequiredArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @NonNull
    @JoinColumn(name="author_id", nullable=false)
    private User user;

    @NonNull
    @JoinColumn(name="message", nullable=false)
    private String message;

    @ManyToOne
    @NonNull
    @JoinColumn(name="reclamation_id", nullable=false)
    private Reclamation reclamation;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
}
