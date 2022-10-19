package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("/accidents")
    public String viewAccidents(Model model) {
        List<Accident> accidentList = accidentService.findAll();
        model.addAttribute("accidents", accidentList);
        return "accidents";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        List<AccidentType> accidentTypeList = accidentTypeService.getAccidentTypes();
        List<Rule> ruleList = ruleService.getRules();
        model.addAttribute(new Accident());
        model.addAttribute("types", accidentTypeList);
        model.addAttribute("rules", ruleList);
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        List<Integer> ids = convertStingArrToIntList(req.getParameterValues("rIds"));
        List<Rule> ruleList = ruleService.getByIds(ids);
        accident.setRules(ruleList);
        int typeId = accident.getType().getId();
        AccidentType accidentType = accidentTypeService.findAccidentTypeById(typeId);
        accident.setType(accidentType);
        accidentService.add(accident);
        return "redirect:/index";
    }

    @PostMapping("/editAccident")
    public String edit(@ModelAttribute Accident accident) {
        int typeId = accident.getType().getId();
        AccidentType accidentType = accidentTypeService.findAccidentTypeById(typeId);
        accident.setType(accidentType);
        accidentService.add(accident);
        return "redirect:/index";
    }

    @GetMapping("/formEditAccident/{AccidentId}")
    public String formEditAccident(Model model, @PathVariable("AccidentId") int id) {
        model.addAttribute("accident", accidentService.findAccidentById(id));
        model.addAttribute("types", accidentTypeService.getAccidentTypes());
        model.addAttribute("rules", ruleService.getRules());
        return "editAccident";
    }

    private List<Integer> convertStingArrToIntList(String[] ids) {
        return Arrays.stream(ids)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }
}