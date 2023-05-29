package ua.kiev.chameleon.notepad.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.kiev.chameleon.notepad.dto.CreateUserDto;
import ua.kiev.chameleon.notepad.dto.EditUserDto;
import ua.kiev.chameleon.notepad.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")

public class AdminController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public EditUserDto showUser(@PathVariable Long id){
        return userService.showUser(id);
    }

    @PostMapping("/edit")
    public String editUser(@RequestBody EditUserDto dto){
        return userService.editUser(dto);
    }

    @PostMapping("/create")
    public String createUser(@RequestBody CreateUserDto dto){
        return userService.createUser(dto);
    }
}
