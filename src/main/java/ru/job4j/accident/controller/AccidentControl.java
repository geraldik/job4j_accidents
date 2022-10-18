package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.List;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final AccidentService accidentService;

    @GetMapping("/accidents")
    public String viewAccidents(Model model) {
        List<Accident> accidentList = accidentService.findAll();
        model.addAttribute("accidents", accidentList);
        return "accidents";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentService.getAccidentTypes());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(accidentService.findAccidentTypeById(typeId));
        accidentService.add(accident);
        return "redirect:/index";
    }

    @PostMapping("/editAccident")
    public String edit(@ModelAttribute Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(accidentService.findAccidentTypeById(typeId));
        accidentService.add(accident);
        return "redirect:/index";
    }

    @GetMapping("/formEditAccident/{AccidentId}")
    public String formEditAccident(Model model, @PathVariable("AccidentId") int id) {
        model.addAttribute("accident", accidentService.findAccidentById(id));
        model.addAttribute("types", accidentService.getAccidentTypes());
        return "editAccident";
    }
}