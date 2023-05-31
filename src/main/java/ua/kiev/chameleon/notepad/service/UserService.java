package ua.kiev.chameleon.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kiev.chameleon.notepad.dto.*;
import ua.kiev.chameleon.notepad.entity.Label;
import ua.kiev.chameleon.notepad.entity.User;
import ua.kiev.chameleon.notepad.repository.LabelRepository;
import ua.kiev.chameleon.notepad.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    private final NoteService noteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Response showUser(Long id) {
        Response response = new Response();
        User user = userRepository.findUserById(id);
        if (user == null) {
            response.setCode("error");
            response.setMessage("Користувача з ID " + id + " існує");
        } else {
            response.setCode("OK");
            response.setPayload(mapUserToUserDto(user));
        }
        return response;
    }

    public Response showMyUser(){
        User user = userRepository.findUserById(noteService.getUserId());
        EditMyUserDto editMyUserDto = new EditMyUserDto();
        editMyUserDto.setUsername(user.getUsername());
        editMyUserDto.setEmail(user.getEmail());
        Response response = new Response();
        response.setCode("OK");
        response.setPayload(editMyUserDto);
        return response;
    }

    public Response editMyUser(EditMyUserDto dto){
        User user = userRepository.findUserById(noteService.getUserId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
        ResponseString responseString = new ResponseString();
        responseString.setResult("Все добре, Ви відредагували свій profile");
        Response response = new Response();
        response.setCode("OK");
        response.setPayload(responseString);
        return response;
    }

    public String editUser(EditUserDto dto){
        User user = userRepository.findUserById(dto.getId());
        user.setUsername(dto.getUsername());
       // user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setEnabled(dto.getEnabled());
        userRepository.save(user);
        return "Все добре, юзвера " + user.getUsername() + " відредагували";
    }

    public String createUser(CreateUserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setEnabled(dto.getEnabled());
        userRepository.save(user);
        Label label = new Label();
        label.setColor("Yellow");
        label.setName("Uncategories");
        label.setUser(user);
        labelRepository.save(label);
        return "Все добре, юзвера " + user.getUsername() + " добавили";
    }
    public Response showAllUser(){
        List<User> users = userRepository.findAll();
        Response response = new Response();
        response.setCode("OK");
        response.setPayload(convertListUserToListUserDto(users));
        return response;
    }

    public Response deleteUser(DeleteUserDto dto){
        ResponseString responseString = new ResponseString();
        Response response = new Response();
        if(!userRepository.existsById(dto.getId())){
            response.setCode("error");
            response.setMessage("Користувача з такитм ID не існує");
            return response;
        }
        User user = userRepository.findUserById(dto.getId());
        String name = user.getUsername();
        userRepository.deleteById(dto.getId());
        responseString.setResult("Все добре, користувача " + name + " видалили");
        response.setCode("OK");
        response.setPayload(responseString);
        return response;
    }

    public EditUserDto mapUserToUserDto(User user){
        EditUserDto editUserDto = new EditUserDto();
        editUserDto.setId(user.getId());
        editUserDto.setUsername(user.getUsername());
        editUserDto.setPassword(user.getPassword());
        editUserDto.setRole(user.getRole());
        editUserDto.setEmail(user.getEmail());
        editUserDto.setEnabled(user.getEnabled());
        return editUserDto;
    }

    public List<EditUserDto> convertListUserToListUserDto(List<User> users) {
        return users.stream().map(this::mapUserToUserDto).toList();
    }
}
