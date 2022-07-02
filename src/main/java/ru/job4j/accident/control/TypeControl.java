package ru.job4j.accident.control;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.TypeService;

import java.util.Optional;

@Controller
@Data
public class TypeControl {
    private final TypeService types;

    @GetMapping("/addType")
    public String addType(Model model) {
        model.addAttribute("types", types.findAll());
        return "settings/addType";
    }

    @PostMapping("saveType")
    public String saveType(@ModelAttribute AccidentType type) {
        type.setName(type.getName().toUpperCase());
        types.add(type);
        return "redirect:/addType";
    }

    @GetMapping("deleteType/{typeId}")
    public String deleteType(@PathVariable("typeId") int id) {
        AccidentType type = types.findById(id).get();
        types.delete(type);
        return "redirect:/addType";
    }

}
