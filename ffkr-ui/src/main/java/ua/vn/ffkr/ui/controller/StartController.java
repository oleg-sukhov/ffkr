package ua.vn.ffkr.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {
    public static final String START_PAGE = "index";

    @RequestMapping("/")
    public String index() {
        return START_PAGE;
    }
}
