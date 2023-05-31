package ua.kiev.chameleon.notepad.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.kiev.chameleon.notepad.dto.*;
import ua.kiev.chameleon.notepad.service.NoteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notepad")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/my-list")
    public Response getAllMyNotesList(){
        return noteService.getAllMyNotesList();
    }

    @GetMapping("/all-list")
    public Response getAllPublicNotesList(){
        return noteService.getAllPublicNotesList();
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
    public Response showNote(@PathVariable Long id){
        return noteService.showNote(id);
    }

    @GetMapping("/access")
    public Response getAccessType(){
        return  noteService.getAccessType();
    }

    @GetMapping("/my-labels")
    public Response getAllMyLabelsList() {
        return noteService.getAllMyLabelsList();
    }

    @PostMapping("/create-label")
    public String createLabel(@RequestBody LabelDto dto) {
        return noteService.createLabel(dto);
    }

}
