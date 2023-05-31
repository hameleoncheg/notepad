package ua.kiev.chameleon.notepad.dto;

import lombok.Data;

@Data
public class Response {
    String code;
    String message;
    Object payload;
}
