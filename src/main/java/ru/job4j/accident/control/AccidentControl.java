package ru.job4j.accident.control;

import lombok.Data;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.RuleService;
import ru.job4j.accident.service.TypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@ThreadSafe
@Controller
@Data
public class AccidentControl {
    private final AccidentService accidentService;
    private final TypeService typeService;
    private final RuleService ruleService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/create";
    }

    /**
     * @RequestParam- позволяет получить параметр из строки запроса.
     */
    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("accident", accidentService.findById(id).get());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/update";
    }

    @GetMapping("/read")
    public String read(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("accident", accidentService.findById(id).get());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/read";
    }

    /**
     * @ModelAttribute позволяет получить параметрЫ из строки запроса.
     * Данные на контроллере мы получаем напрямую из запроса HttpRequestServlet.
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        setAccident(accident, req);
        accidentService.add(accident);
        return "redirect:/";
    }

    @PostMapping("saveAfterUpdate")
    public String saveAfterUpdate(@ModelAttribute Accident accident, HttpServletRequest req) {
        setAccident(accident, req);
        accidentService.update(accident);
        return "redirect:/";
    }
    @GetMapping("delete/{accId}")
    public String delete(@PathVariable ("accId") int id) {
        Accident accident = accidentService.findById(id).get();
        accidentService.delete(accident);
        return "redirect:/";
    }

    private void setAccident(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setType(typeService.findById(accident.getType().getId()).get());
        String[] ids = req.getParameterValues("rIds");
        Set<Rule> rules = new HashSet<>();
        for (String id : ids) {
            rules.add(ruleService.findById(Integer.parseInt(id)).get());
        }
        accident.setRules(rules);
    }

}
