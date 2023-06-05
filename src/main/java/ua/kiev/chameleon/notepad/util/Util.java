package ua.kiev.chameleon.notepad.util;

import ua.kiev.chameleon.notepad.dto.EditUserDto;
import ua.kiev.chameleon.notepad.dto.NoteDto;
import ua.kiev.chameleon.notepad.dto.Response;
import ua.kiev.chameleon.notepad.dto.ResponseString;
import ua.kiev.chameleon.notepad.entity.Note;
import ua.kiev.chameleon.notepad.entity.User;

import java.util.List;

public class Util {
    public static EditUserDto mapUserToUserDto(User user){
        EditUserDto editUserDto = new EditUserDto();
        editUserDto.setId(user.getId());
        editUserDto.setUsername(user.getUsername());
        editUserDto.setPassword(user.getPassword());
        editUserDto.setRole(user.getRole());
        editUserDto.setEmail(user.getEmail());
        editUserDto.setEnabled(user.getEnabled());
        return editUserDto;
    }

    public static List<EditUserDto> convertListUserToListUserDto(List<User> users) {
        return users.stream().map(Util::mapUserToUserDto).toList();
    }

    public static Response createOkStringAnswer(String result){
        ResponseString responseString = new ResponseString();
        responseString.setResult(result);
        Response response = new Response();
        response.setCode("OK");
        response.setPayload(responseString);
        return response;
    }

    public static Response createErrorStringAnswer(String massage){
        Response response = new Response();
        response.setCode("error");
        response.setMessage(massage);
        return response;
    }

    public static Response createOkObjectAnswer(Object o){
        Response response = new Response();
        response.setCode("OK");
       response.setPayload(o);
        return response;
    }

    public  static NoteDto mapNoteToNoteDto(Note note){
        NoteDto noteDto = new NoteDto();
        noteDto.setId(note.getId());
        noteDto.setIndex(note.getIndex());
        noteDto.setTitle(note.getTitle());
        noteDto.setContent(note.getContent());
        noteDto.setAccessType(note.getAccessType());
        noteDto.setLabel(note.getLabel());
        noteDto.setCreatedAt(note.getCreatedAt());
        noteDto.setEditedAt(note.getEditedAt());
        noteDto.setUsername(note.getUser().getUsername());
        return noteDto;
    }
    public  static List<NoteDto> convertListNoteToListNoteDto(List<Note> notes) {
        return notes.stream().map(Util::mapNoteToNoteDto).toList();
    }


}
