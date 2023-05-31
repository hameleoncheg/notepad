package ua.kiev.chameleon.notepad.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.kiev.chameleon.notepad.dto.EditMyUserDto;
import ua.kiev.chameleon.notepad.dto.Response;
import ua.kiev.chameleon.notepad.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public Response showMyUser(){
        return userService.showMyUser();
    }

    @PostMapping("/edit")
    public Response editMyUser(@RequestBody EditMyUserDto dto){
        return userService.editMyUser(dto);
    }

}
