package life.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name",
            required=false, defaultValue="World") String name,
                           @RequestParam(name = "pwd",
                                   required=false, defaultValue="World") String pwd
            ,Model model) {
        model.addAttribute("name", name);
        model.addAttribute("pwd",pwd);
        return "greeting";
    }

}
