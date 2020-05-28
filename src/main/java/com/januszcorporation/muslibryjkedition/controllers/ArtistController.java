package com.januszcorporation.muslibryjkedition.controllers;

import com.januszcorporation.muslibryjkedition.repositories.ArtistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArtistController {

    private ArtistRepository artistRepository;

    private ArtistController(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    @RequestMapping("artists")
    public String getArtists(Model model){
        model.addAttribute("artists", artistRepository.findAll());
        return "artists";
    }
}
