package ua.kiev.chameleon.notepad.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.kiev.chameleon.notepad.dto.EditMyUserDto;
import ua.kiev.chameleon.notepad.dto.EditUserDto;
import ua.kiev.chameleon.notepad.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public EditMyUserDto showMyUser(){
        return userService.showMyUser();
    }

    @PostMapping("/edit")
    public String  editMyUser(@RequestBody EditMyUserDto dto){
        return userService.editMyUser(dto);
    }

}
