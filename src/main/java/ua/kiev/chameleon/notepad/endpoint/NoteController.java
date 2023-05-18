package ua.kiev.chameleon.notepad.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.chameleon.notepad.entity.AccessType;
import ua.kiev.chameleon.notepad.entity.Note;
import ua.kiev.chameleon.notepad.service.NoteService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;
   // private final ConversionService conversionService;

//    @PostMapping ("/create")
//    public ModelAndView add(@RequestParam String title,
//                            @RequestParam String content,
//                            @RequestParam String accessType) {
//        final Note note = new Note(title,content,accessType,noteService.getUserId());
//
//        return null;
//    }
}
