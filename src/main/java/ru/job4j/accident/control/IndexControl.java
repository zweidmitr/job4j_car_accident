package ru.job4j.accident.control;

import lombok.Data;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.TypeService;

import java.util.List;

@ThreadSafe
@Controller
@Data
public class IndexControl {

    private final AccidentService accidentService;
    private final TypeService typeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        model.addAttribute("types", typeService.findAll());
        return "index";
    }
}
