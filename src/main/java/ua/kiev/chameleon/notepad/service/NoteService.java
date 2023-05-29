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

    public String createNote(CreateNoteDto dto) {
        Note note = new Note();
        Label label = labelRepository.findByNameAndUser_Id(dto.getLabel(), getUserId());
        note.setCreatedAt(LocalDateTime.now());
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setAccessType(dto.getAccessType());
        note.setLabel(label);
        note.setUser(getUser());
        noteRepository.save(note);
        return "Все добре, нотатку " + note.getTitle() + " додали";
    }

    public String deleteNote(DeleteNoteDto dto) {
        Note note = getById(dto.getId());
        String title = note.getTitle();
        noteRepository.deleteById(dto.getId());
        return "Нотатку " + title + " видалили";
    }

    public List<NoteDto> getAllMyNotesList() {
        List<Note> notes = noteRepository.findAllByUserId(getUserId());
        return convertListNoteToListNoteDto(notes);
    }

    public List<NoteDto> getAllPublicNotesList() {
        List<Note> notes = noteRepository.findAll()
                .stream()
                .filter(o-> Objects.equals(o.getAccessType(), AccessType.PUBLIC))
                .collect(Collectors.toList());
        return convertListNoteToListNoteDto(notes);
    }

    public String  editNote(EditNoteDto dto) {
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

    public NoteDto showNote(Long id){
        return mapNoteToNoteDto(getById(id));
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

    public  AccessType[] getAccessType() {
        return AccessType.values();
    }

    public List<NoteDto> convertListNoteToListNoteDto(List<Note> notes) {
        return notes.stream().map(this::mapNoteToNoteDto).toList();
    }

    public NoteDto mapNoteToNoteDto(Note note){
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

    public List<Note>  listAll() {
        return noteRepository.findAll();
    }

    public List<Label>  getAllMyLabelsList() {
        return labelRepository.findAllByUser_Id(getUserId());
    }

    public String  createLabel(LabelDto dto) {
        Label label = new Label();
        label.setName(dto.getName());
        label.setColor(dto.getColor());
        label.setUser(getUser());
        labelRepository.save(label);
        return "Категорію " + label.getName() + " створено";
    }
}
