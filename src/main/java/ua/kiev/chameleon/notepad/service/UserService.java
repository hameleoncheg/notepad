package ua.kiev.chameleon.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kiev.chameleon.notepad.util.Util;
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
        User user = userRepository.findUserById(id);
        if (user == null) {
            return Util.createErrorStringAnswer("Користувача з ID " + id + " існує");
        } else {
            return Util.createOkObjectAnswer(Util.mapUserToUserDto(user));
        }
    }

    public Response showMyUser(){
        User user = userRepository.findUserById(noteService.getUserId());
        EditMyUserDto editMyUserDto = new EditMyUserDto();
        editMyUserDto.setUsername(user.getUsername());
        editMyUserDto.setEmail(user.getEmail());
        return Util.createOkObjectAnswer(editMyUserDto);
    }

    public Response editMyUser(EditMyUserDto dto){
        User user = userRepository.findUserById(noteService.getUserId());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        userRepository.save(user);
        return Util.createOkStringAnswer("Все добре, Ви відредагували свій profile");
    }

    public Response editUser(EditUserDto dto){
        User user = userRepository.findUserById(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setEnabled(dto.getEnabled());
        userRepository.save(user);
        return Util.createOkStringAnswer("Все добре, юзвера " + user.getUsername() + " відредагували");
    }

    public Response createUser(CreateUserDto dto){
        if(userRepository.findUserByEmail(dto.getEmail()) != null){
            return Util.createErrorStringAnswer("Користувач  з таким Email " + dto.getEmail() + "  вже існує");
        } else if (userRepository.findByUsername(dto.getUsername()) != null){
            return Util.createErrorStringAnswer("Користувач  з таким ім'ям " + dto.getUsername() + "  вже існує");
        }
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
        return Util.createOkStringAnswer("Все добре, юзвера " + user.getUsername() + " добавили");
    }
    public Response showAllUser(){
        List<User> users = userRepository.findAll();
        return Util.createOkObjectAnswer(Util.convertListUserToListUserDto(users));
    }

    public Response deleteUser(DeleteUserDto dto){
        if(!userRepository.existsById(dto.getId())){
            return Util.createErrorStringAnswer("Користувача з такитм ID не існує");
        }
        String name = userRepository.findUserById(dto.getId()).getUsername();
        userRepository.deleteById(dto.getId());
        return Util.createOkStringAnswer("Все добре, користувача " + name + " видалили");
    }

}
