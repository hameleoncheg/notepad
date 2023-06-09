package ua.kiev.chameleon.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.chameleon.notepad.entity.Note;
import ua.kiev.chameleon.notepad.entity.User;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUserId(long id);
    Note findNoteById(Long id);

}
