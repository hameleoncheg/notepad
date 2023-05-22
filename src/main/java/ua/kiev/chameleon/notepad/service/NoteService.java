package ua.kiev.chameleon.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.kiev.chameleon.notepad.dto.DeleteNoteDto;
import ua.kiev.chameleon.notepad.dto.EditNoteDto;
import ua.kiev.chameleon.notepad.dto.CreateNoteDto;
import ua.kiev.chameleon.notepad.dto.NoteDto;
import ua.kiev.chameleon.notepad.entity.AccessType;
import ua.kiev.chameleon.notepad.entity.Note;
import ua.kiev.chameleon.notepad.entity.User;
import ua.kiev.chameleon.notepad.repository.NoteRepository;
import ua.kiev.chameleon.notepad.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public String addNewNote(CreateNoteDto dto) {
        Note note = new Note();
        note.setCreatedAt(LocalDateTime.now());
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setAccessType(dto.getAccessType());
        note.setUser(getUser());
        noteRepository.save(note);
        return "Все добре, нотатку " + note.getTitle() + " додали";
    }

    public String deleteById(DeleteNoteDto dto) {
        Note note = getById(dto.getId());
        String title = note.getTitle();
        noteRepository.deleteById(dto.getId());
        return "Нотатку " + title + " видалили";
    }

    public List<NoteDto> listAll() {
        List<Note> notes = noteRepository.findAllByUserId(getUserId());
        List<NoteDto> dtos = new ArrayList<>();
        for (Note note: notes){
            NoteDto dto = new NoteDto();
            dto.setId(note.getId());
            dto.setIndex(note.getIndex());
            dto.setTitle(note.getTitle());
            dto.setContent(note.getContent());
            dto.setAccessType(note.getAccessType());
            dto.setCreatedAt(note.getCreatedAt());
            dto.setEditedAt(note.getEditedAt());
            dtos.add(dto);
        }
        return dtos;
    }

    public String  updateNote(EditNoteDto dto) {
        long id = dto.getId();
        if (!noteRepository.existsById(id)) {
            throw new IllegalArgumentException("Note with id=" + id + " does not exist");
        }
        Note note = getById(dto.getId());
        note.setEditedAt(LocalDateTime.now());
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setAccessType(dto.getAccessType());
        noteRepository.save(note);
        return "Все добре, нотатку з ID " + note.getId() + " відредагували";
    }

    public List<Note> listAllByPublic() {
        return noteRepository.findAll()
                .stream()
                .filter(o-> Objects.equals(o.getAccessType(), AccessType.PUBLIC))
                .collect(Collectors.toList());
    }

    public Note getById(long id) {
        Note note = noteRepository.getReferenceById(id);
        return note;
    }

    public User getUser() {
        return  userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public Long getUserId() {
        final User principal = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return principal.getId();
    }



}
