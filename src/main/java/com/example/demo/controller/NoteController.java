package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController {
    final String viewDir = "note";

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Note> noteList = noteRepository.findAll();
        model.addAttribute("noteList", noteList);
        return viewDir + "/index";
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Note note, ModelMap model) {
        Note saveNoteInstance = noteRepository.save(note);
        model.addAttribute("id", saveNoteInstance.getId());

        return new ModelAndView("redirect:/show", model);
    }

    @GetMapping("/show")
    public String show(
        @RequestParam(name = "id", required = true, defaultValue = "0") String id, Model model) throws Throwable {

        Note noteInstance = noteRepository.findById(Long.parseLong(id)).orElseThrow(() -> new Throwable("Note not exists"));
        model.addAttribute("noteInstance", noteInstance);

        return viewDir + "/show";
    }
}