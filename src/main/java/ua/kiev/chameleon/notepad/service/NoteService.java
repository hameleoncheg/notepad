package ua.kiev.chameleon.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.kiev.chameleon.notepad.entity.Note;
import ua.kiev.chameleon.notepad.entity.User;
import ua.kiev.chameleon.notepad.repository.NoteRepository;
import ua.kiev.chameleon.notepad.repository.UserRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public Note add(Note note) {
        note.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        noteRepository.save(note);
        return note;
    }
    public Long getUserId() {
        final User principal = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return principal.getId();
    }

}
