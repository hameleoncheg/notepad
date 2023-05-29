package ua.kiev.chameleon.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.kiev.chameleon.notepad.dto.CreateUserDto;
import ua.kiev.chameleon.notepad.dto.EditUserDto;
import ua.kiev.chameleon.notepad.entity.Label;
import ua.kiev.chameleon.notepad.entity.User;
import ua.kiev.chameleon.notepad.repository.LabelRepository;
import ua.kiev.chameleon.notepad.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final LabelRepository labelRepository;

    public EditUserDto showUser(Long id){
        User user = userRepository.findUserById(id);
        EditUserDto editUserDto = new EditUserDto();
        editUserDto.setId(user.getId());
        editUserDto.setUsername(user.getUsername());
        editUserDto.setPassword(user.getPassword());
        editUserDto.setRole(user.getRole());
        editUserDto.setEmail(user.getEmail());
        editUserDto.setEnabled(user.getEnabled());
        return editUserDto;

    }

    public String editUser(EditUserDto dto){
        User user = userRepository.findUserById(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEnabled(dto.getEnabled());
        userRepository.save(user);
        return "Все добре, юзвера " + user.getUsername() + " відредагували";
    }

    public String createUser(CreateUserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
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

}
