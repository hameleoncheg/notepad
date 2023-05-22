package ua.kiev.chameleon.notepad.dto;

import lombok.Data;
import ua.kiev.chameleon.notepad.entity.AccessType;

@Data
public class EditNoteDto {
    private long id;
    private String title;
    private String content;
    private AccessType accessType;
}
