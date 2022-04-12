package com.limpet1.controllers;

import com.limpet1.model.XUser;
import com.limpet1.repository.IcoRepository;
import com.limpet1.repository.UserRepositoryJPA;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class MainController {

    private static final String MAIN = "main";
    private final IcoRepository icoRepository;
    private final UserRepositoryJPA userRepositoryJPA;

    public MainController(IcoRepository icoRepository, UserRepositoryJPA userRepositoryJPA) {
        this.icoRepository = icoRepository;
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @GetMapping("/main")
    public String mainPage(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        XUser xUser = userRepositoryJPA.findByEmail(user.getUsername());

        List<String> list = Arrays.asList("Name", "Rated", "Date", "About");
        List<Map<String, Object>> columns = new ArrayList<>();


        var icoList = icoRepository.findAll();

        for (var i : icoList) {

            columns.add(Map.of("Name", i.getName(), "Rated", i.getRated(),
                    "Date", i.getDate(), "About" , i.getAbout()));
        }


        model.addAttribute("xUser", xUser);
        model.addAttribute("list", list);
        model.addAttribute("columns", columns);

        return MAIN;
    }
}