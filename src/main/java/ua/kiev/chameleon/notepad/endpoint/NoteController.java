package ua.kiev.chameleon.notepad.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;
import ua.kiev.chameleon.notepad.dto.DeleteNoteDTO;
import ua.kiev.chameleon.notepad.dto.NoteDto;
import ua.kiev.chameleon.notepad.entity.Note;
import ua.kiev.chameleon.notepad.service.NoteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notepad")
public class NoteController {

    private final NoteService noteService;
    private final ConversionService conversionService;

//    @PostMapping ("/create")
//    public ModelAndView add(@RequestParam String title,
//                            @RequestParam String content,
//                            @RequestParam String accessType) {
//        final Note note = new Note(title,content,accessType,noteService.getUserId());
//
//        return null;
//    }

    @GetMapping("/edit")
    public Note edit(@RequestParam ("id") Long id){
        Note note = noteService.getById(id);
        System.out.println(note);
        return note;
    }

    @PostMapping ("/edit")
    public Object editById(@RequestParam Long id,
                    @RequestParam String title,
                    @RequestParam String content,
                    @RequestParam String accessType){
       // Map<String, Object> object = new HashMap<>();
        return null;
    }

    @GetMapping("/list")
    public List<Note> getNotesList(){
        List<Note> result = noteService.listAll();
        return result;
    }

    @PostMapping("/create")
    public String add(@RequestBody NoteDto dto) {
        return noteService.add(dto);
    }

    @PostMapping("/delete")
    public String  delete(@RequestBody DeleteNoteDTO dto) {
        return  noteService.deleteById(dto);
    }


}
