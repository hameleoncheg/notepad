package ua.kiev.chameleon.notepad.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.kiev.chameleon.notepad.dto.DeleteNoteDto;
import ua.kiev.chameleon.notepad.dto.EditNoteDto;
import ua.kiev.chameleon.notepad.dto.CreateNoteDto;
import ua.kiev.chameleon.notepad.dto.NoteDto;
import ua.kiev.chameleon.notepad.entity.Note;
import ua.kiev.chameleon.notepad.service.NoteService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notepad")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/my-list")
    public List<NoteDto> getNotesList(){
        List<NoteDto> result = noteService.listAll();
        return result;
    }

    @GetMapping("/all-list")
    public List<Note> getPublicNotesList(){
        List<Note> result = noteService.listAllByPublic();
        return result;
    }

    @PostMapping("/create")
    public String add(@RequestBody CreateNoteDto dto) {
        return noteService.addNewNote(dto);
    }

    @PostMapping("/delete")
    public String  delete(@RequestBody DeleteNoteDto dto) {
        return  noteService.deleteById(dto);
    }

    @PostMapping("/edit")
    public String add(@RequestBody EditNoteDto dto) {
        return noteService.updateNote(dto);
    }

    @GetMapping("/note/{id}")
    public Note showNote(@PathVariable Long id){
        return noteService.getById(id);
    }

}
