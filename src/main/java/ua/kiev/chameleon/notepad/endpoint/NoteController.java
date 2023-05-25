package ua.kiev.chameleon.notepad.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.kiev.chameleon.notepad.dto.DeleteNoteDto;
import ua.kiev.chameleon.notepad.dto.EditNoteDto;
import ua.kiev.chameleon.notepad.dto.CreateNoteDto;
import ua.kiev.chameleon.notepad.dto.NoteDto;
import ua.kiev.chameleon.notepad.entity.AccessType;
import ua.kiev.chameleon.notepad.entity.Note;
import ua.kiev.chameleon.notepad.service.NoteService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notepad")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/my-list")
    public List<NoteDto> getAllMyNotesList(){
        List<NoteDto> result = noteService.getAllMyNotesList();
        return result;
    }

    @GetMapping("/all-list")
    public List<NoteDto> getAllPublicNotesList(){
        List<NoteDto> result = noteService.getAllPublicNotesList();
        return result;
    }

    @PostMapping("/create")
    public String createNote(@RequestBody CreateNoteDto dto) {
        return noteService.createNote(dto);
    }

    @PostMapping("/delete")
    public String  deleteNote(@RequestBody DeleteNoteDto dto) {
        return  noteService.deleteNote(dto);
    }

    @PostMapping("/edit")
    public String editNote(@RequestBody EditNoteDto dto) {
        return noteService.editNote(dto);
    }

    @GetMapping("/note/{id}")
    public NoteDto showNote(@PathVariable Long id){
        return noteService.showNote(id);
    }

    @GetMapping("/access")
    public AccessType[] getAccessType(){
        return  noteService.getAccessType();
    }

    @GetMapping("/list")
    public List<Note> list() {
        return noteService.listAll();
    }

}
