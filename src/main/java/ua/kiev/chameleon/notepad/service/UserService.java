package ua.kiev.chameleon.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kiev.chameleon.notepad.dto.CreateUserDto;
import ua.kiev.chameleon.notepad.dto.DeleteUserDto;
import ua.kiev.chameleon.notepad.dto.EditMyUserDto;
import ua.kiev.chameleon.notepad.dto.EditUserDto;
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


    public EditUserDto showUser(Long id){
        User user = userRepository.findUserById(id);
        return mapUserToUserDto(user);
    }
    public EditMyUserDto showMyUser(){
        User user = userRepository.findUserById(noteService.getUserId());
        EditMyUserDto editMyUserDto = new EditMyUserDto();
        editMyUserDto.setUsername(user.getUsername());
        editMyUserDto.setEmail(user.getEmail());
        return editMyUserDto;
    }

    public String editMyUser(EditMyUserDto dto){
        User user = userRepository.findUserById(noteService.getUserId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
        return "Все добре, Ви відредгували свій profile";
    }

    public String editUser(EditUserDto dto){
        User user = userRepository.findUserById(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setEnabled(dto.getEnabled());
        userRepository.save(user);
        return "Все добре, юзвера " + user.getUsername() + " відредагували";
    }

    public String createUser(CreateUserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
       // user.setPassword(dto.getPassword());
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
    public List<EditUserDto> showAllUser(){
        List<User> users = userRepository.findAll();
        return convertListUserToListUserDto(users);
    }

    public String deleteUser(DeleteUserDto dto){
        User user = userRepository.findUserById(dto.getId());
        String name = user.getUsername();
        userRepository.deleteById(dto.getId());
        return "Все добре, юзвера " + name + " видалили";
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
