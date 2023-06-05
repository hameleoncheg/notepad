package ua.kiev.chameleon.notepad.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.kiev.chameleon.notepad.dto.CreateUserDto;
import ua.kiev.chameleon.notepad.dto.DeleteUserDto;
import ua.kiev.chameleon.notepad.dto.EditUserDto;
import ua.kiev.chameleon.notepad.dto.Response;
import ua.kiev.chameleon.notepad.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")

public class AdminController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public Response showUser(@PathVariable Long id){
        return userService.showUser(id);
    }

    @PostMapping("/edit")
    public Response editUser(@RequestBody EditUserDto dto){
        return userService.editUser(dto);
    }

    @PostMapping("/create")
    public Response createUser(@RequestBody CreateUserDto dto){
        return userService.createUser(dto);
    }

    @GetMapping("/all-users")
    public Response showAllUser(){
        return userService.showAllUser();
    }

    @PostMapping("/delete")
    public Response  deleteUser(@RequestBody DeleteUserDto dto) {
        return  userService.deleteUser(dto);
    }
}
