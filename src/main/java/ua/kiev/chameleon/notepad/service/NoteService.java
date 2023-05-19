package ua.kiev.chameleon.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.kiev.chameleon.notepad.dto.DeleteNoteDTO;
import ua.kiev.chameleon.notepad.dto.NoteDto;
import ua.kiev.chameleon.notepad.entity.Note;
import ua.kiev.chameleon.notepad.entity.User;
import ua.kiev.chameleon.notepad.repository.NoteRepository;
import ua.kiev.chameleon.notepad.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public String add(NoteDto dto) {
        Note note = new Note();
        note.setCreatedAt(LocalDateTime.now());
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setAccessType(dto.getAccessType());
        note.setUser(getUserId());
        noteRepository.save(note);
        return "Все добре, нотатку " + note.getTitle() + " додали";
    }

    public String deleteById(DeleteNoteDTO dto) {
        Note note = getById(dto.getId());
        String title = note.getTitle();
        noteRepository.deleteById(dto.getId());
        return "Нотатку " + title + " видалили";
    }

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public Note getById(long id) {
        Note note = noteRepository.getReferenceById(id);
        return note;
    }

    public void edit(Note note) {
        long id = note.getId();
        if (!noteRepository.existsById(id)) {
            throw new IllegalArgumentException("Note with id=" + id + " does not exist");
        }
        noteRepository.save(note);
    }

    public User getUserId() {
        return  userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }





}
