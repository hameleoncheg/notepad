package ua.kiev.chameleon.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.chameleon.notepad.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
