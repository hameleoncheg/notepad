package ua.kiev.chameleon.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.kiev.chameleon.notepad.dto.*;
import ua.kiev.chameleon.notepad.entity.AccessType;
import ua.kiev.chameleon.notepad.entity.Label;
import ua.kiev.chameleon.notepad.entity.Note;
import ua.kiev.chameleon.notepad.entity.User;
import ua.kiev.chameleon.notepad.repository.LabelRepository;
import ua.kiev.chameleon.notepad.repository.NoteRepository;
import ua.kiev.chameleon.notepad.repository.UserRepository;
import ua.kiev.chameleon.notepad.util.Util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;

    public Response createNote(CreateNoteDto dto) {
        Note note = new Note();
        Label label = labelRepository.findByNameAndUser_Id(dto.getLabel(), getUserId());
        note.setCreatedAt(LocalDateTime.now());
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setAccessType(dto.getAccessType());
        note.setLabel(label);
        note.setUser(getUser());
        noteRepository.save(note);
        return Util.createOkStringAnswer("Все добре, нотатку " + note.getTitle() + " додали");
    }

    public Response deleteNote(DeleteNoteDto dto) {
        if(!noteRepository.existsById(dto.getId())){
            return Util.createErrorStringAnswer("Нотатки  з ID " + dto.getId() + " не існує");
        }
         String noteTitle = getById(dto.getId()).getTitle();
         if(getUser().getRole().equals("ADMIN")) {
            noteRepository.deleteById(dto.getId());
            return Util.createOkStringAnswer("Нотатка " + noteTitle + " видалена");
        } else if(noteRepository.findNoteById(dto.getId()).getUser().getId() != getUserId()){
            return Util.createErrorStringAnswer("Нотатка Вам не належить");
        }
            noteRepository.deleteById(dto.getId());
            return Util.createOkStringAnswer("Нотатка " + noteTitle + " видалена");
    }

    public Response getAllMyNotesList() {
        List<Note> notes = noteRepository.findAllByUserId(getUserId());
        return Util.createOkObjectAnswer(Util.convertListNoteToListNoteDto(notes));
    }

    public Response getAllPublicNotesList() {
        List<Note> notes = noteRepository.findAll()
                .stream()
                .filter(o-> Objects.equals(o.getAccessType(), AccessType.PUBLIC))
                .collect(Collectors.toList());
        return Util.createOkObjectAnswer(Util.convertListNoteToListNoteDto(notes));
    }

    public Response  editNote(EditNoteDto dto) {
        if (!noteRepository.existsById(dto.getId())) {
            return Util.createErrorStringAnswer("Нотатки з ID " + dto.getId() + " не існує");
        } else if(dto.getTitle().length() >= 20) {
            return Util.createErrorStringAnswer( "Назва нотатки не повинна перевищувати 20 символів");
        }
        Note note = getById(dto.getId());
        note.setEditedAt(LocalDateTime.now());
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setAccessType(dto.getAccessType());
        noteRepository.save(note);
        return Util.createOkStringAnswer("Все добре, нотатку з ID " + note.getId() + " відредагували");
    }

    public Response showNote(Long id){
        if(!noteRepository.existsById(id)){
            return Util.createErrorStringAnswer("Нотатки  з ID " + id + " не існує");
        }else if(getUser().getRole().equals("ADMIN")) {
            return Util.createOkObjectAnswer(Util.mapNoteToNoteDto(getById(id)));
        } else if(noteRepository.findNoteById(id).getUser().getId() != getUserId()){
            return Util.createErrorStringAnswer("Нотатки  з ID" + id + " Вам не належить");
        }
        return Util.createOkObjectAnswer(Util.mapNoteToNoteDto(getById(id)));
    }

    public Note getById(long id) {
        return noteRepository.getReferenceById(id);
    }

    public User getUser() {
        return  userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public Long getUserId() {
        final User principal = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return principal.getId();
    }

    public Response getAccessType() {
        return Util.createOkObjectAnswer(AccessType.values());
    }

    public Response getAllMyLabelsList() {
        return Util.createOkObjectAnswer(labelRepository.findAllByUser_Id(getUserId()));
    }

    public Response createLabel(LabelDto dto) {
        Label label = new Label();
        label.setName(dto.getName());
        label.setColor(dto.getColor());
        label.setUser(getUser());
        labelRepository.save(label);
        return Util.createOkStringAnswer("Категорію " + label.getName() + " створено");
    }

}
