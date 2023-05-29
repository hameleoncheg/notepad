package ua.kiev.chameleon.notepad.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String password;
    private String role;
    private String email;
    private int enabled;
}
