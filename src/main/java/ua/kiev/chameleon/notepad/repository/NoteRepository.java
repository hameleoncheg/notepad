package ua.kiev.chameleon.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.chameleon.notepad.entity.Note;
import ua.kiev.chameleon.notepad.entity.User;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    LinkedList<Note> findAllByUserId(long id);


}
