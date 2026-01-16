package com.openclassrooms.pocwebsocket.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MessageDTO {
    private String message;
    private Integer author_id;
    private Integer reclamation_id;
}
