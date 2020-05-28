package com.januszcorporation.muslibryjkedition.controllers;

import com.januszcorporation.muslibryjkedition.repositories.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SongController {

    private SongRepository songRepository;

    private SongController(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    @RequestMapping("/songs")
    public String getSongs(Model model){
        model.addAttribute("songs", songRepository.findAll());
        return "songs";
    }





}
