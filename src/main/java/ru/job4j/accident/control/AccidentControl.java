package ru.job4j.accident.control;

import lombok.Data;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.TypeService;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
@Controller
@Data
public class AccidentControl {
    private final AccidentService accidentService;
    private final TypeService typeService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", typeService.findAll());
        return "accident/create";
    }

    /**
     * @ModelAttribute позволяет получить параметрЫ из строки запроса.
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accident.setType(typeService.findById(accident.getType().getId()));
        accidentService.add(accident);
        return "redirect:/";
    }

    @PostMapping("saveAfterUpdate")
    public String saveAfterUpdate(@ModelAttribute Accident accident) {
        accident.setType(typeService.findById(accident.getType().getId()));
        accidentService.update(accident);
        return "redirect:/";
    }

    /**
     * @RequestParam- позволяет получить параметр из строки запроса.
     */
    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("accident", accidentService.findById(id));
        return "accident/update";
    }

}
