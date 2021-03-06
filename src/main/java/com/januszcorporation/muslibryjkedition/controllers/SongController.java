package com.januszcorporation.muslibryjkedition.controllers;

import com.januszcorporation.muslibryjkedition.commands.SongCommand;
import com.januszcorporation.muslibryjkedition.converters.SongCommandToSong;
import com.januszcorporation.muslibryjkedition.model.Song;
import com.januszcorporation.muslibryjkedition.repositories.ArtistRepository;
import com.januszcorporation.muslibryjkedition.repositories.PublisherRepository;
import com.januszcorporation.muslibryjkedition.repositories.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SongController {

    private SongRepository songRepository;
    private SongCommandToSong songCommandToSong;
    private PublisherRepository publisherRepository;
    private ArtistRepository artistRepository;

    private SongController(SongRepository songRepository, PublisherRepository publisherRepository, ArtistRepository artistRepository, SongCommandToSong songCommandToSong){
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.publisherRepository = publisherRepository;
        this.songCommandToSong = songCommandToSong;
    }

    @GetMapping
    @RequestMapping(value = {"/songs", "song/list"})
    public String getSongs(Model model){
        model.addAttribute("songs", songRepository.findAll());
        return "songs/list";
    }

    @GetMapping
    @RequestMapping("/song/{id}/show")
    public String getSongDetails(Model model, @PathVariable("id") Long id){
        model.addAttribute("song", songRepository.findById(id).get());
        return "song/show";
    }

    @GetMapping
    @RequestMapping("/song/{id}/delete")
    public String deleteSong(@PathVariable("id") Long id){
        songRepository.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping
    @RequestMapping("/song/new")
    public String newSong(Model model){
        model.addAttribute("song", new SongCommand());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("artists", artistRepository.findAll());
        return "song/addedit";
    }

    @PostMapping("song")
    public String saveOrUpdate(@ModelAttribute SongCommand command){
        Song detachedSong = songCommandToSong.convert(command);
        Song savedSong = songRepository.save(detachedSong);

        return "redirect:/song" + savedSong.getId() + "/show";
    }
}