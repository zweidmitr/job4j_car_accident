package ru.job4j.accident.control;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.RuleService;

import javax.swing.text.html.Option;

@Controller
@Data
public class RuleControl {
    private final RuleService rules;

    @GetMapping("/addRule")
    public String addRule(Model model) {
        model.addAttribute("rules", rules.findAll());
        return "settings/addRule";
    }

    @PostMapping("saveRule")
    public String saveRule(@ModelAttribute Rule rule) {
        rule.setName(rule.getName().toUpperCase());
        rules.add(rule);
        return "redirect:/addRule";
    }

    @GetMapping("deleteRule/{ruleId}")
    public String deleteRule(@PathVariable("ruleId") int id) {
        Rule rule = rules.findById(id).get();
        rules.delete(rule);
        return "redirect:/addRule";
    }

}
