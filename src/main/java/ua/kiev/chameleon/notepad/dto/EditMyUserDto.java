package ua.kiev.chameleon.notepad.dto;

import lombok.Data;

@Data
public class EditMyUserDto {
    private String username;
    private String password;
    private String email;
}
