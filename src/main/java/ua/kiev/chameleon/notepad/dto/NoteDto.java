package ua.kiev.chameleon.notepad.dto;

import lombok.Data;
import ua.kiev.chameleon.notepad.entity.AccessType;
import java.time.LocalDateTime;

@Data
public class NoteDto {
    private long id;
    private int index;
    private String title;
    private String content;
    private AccessType accessType;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
    private String username;
}
