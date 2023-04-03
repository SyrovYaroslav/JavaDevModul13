package com.note.JavaDevModul13;

import com.note.JavaDevModul13.entity.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("index");
        result.addObject("notes", noteService.listAll());
        return result;
    }

    @GetMapping("/create")
    public ModelAndView createView() {
        return new ModelAndView("create_editor");
    }

    @PostMapping ("/create")
    public RedirectView create(@RequestParam(name = "Title") String title, @RequestParam(name = "Content") String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteService.add(note);
        return new RedirectView("/note/list");
    }
    @PostMapping ("/delete")
    public RedirectView delete(@RequestParam(name = "id") long id) {
        noteService.deleteById(id);
        return new RedirectView("/note/list");
    }

    @GetMapping ("/edit")
    public ModelAndView update(@RequestParam(name = "id") long id) {
        ModelAndView result = new ModelAndView("update");
        result.addObject("note", noteService.getById(id));
        return result;
    }

    @PostMapping ("/edit")
    public RedirectView edit(@RequestParam(name = "id") long id,
                             @RequestParam(name = "Title") String title ,
                             @RequestParam(name = "Content") String content) {
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        noteService.update(note);
        return new RedirectView("/note/list");
    }

}
