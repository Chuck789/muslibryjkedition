package com.januszcorporation.muslibryjkedition.controllers;

import com.januszcorporation.muslibryjkedition.commands.ArtistCommand;
import com.januszcorporation.muslibryjkedition.converters.ArtistCommandToArtist;
import com.januszcorporation.muslibryjkedition.model.Artist;
import com.januszcorporation.muslibryjkedition.repositories.ArtistRepository;
import com.januszcorporation.muslibryjkedition.repositories.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ArtistController {

    private ArtistRepository artistRepository;
    private ArtistCommandToArtist artistCommandToArtist;
    private SongRepository songRepository;

    private ArtistController(ArtistRepository artistRepository, SongRepository songRepository){
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }

    @RequestMapping("artists")
    public String getArtists(Model model){
        model.addAttribute("artists", artistRepository.findAll());
        return "artist/list";
    }

    @RequestMapping("/artist/{id}/show")
    public String getArtistDetails(Model model, @PathVariable("id") Long id){
        model.addAttribute("artist", artistRepository.findById(id).get());
        return "artist/show";
    }

    @RequestMapping("/artist/{id}/delete")
    public String deleteArtist(@PathVariable("id") Long id){
        artistRepository.deleteById(id);
        return "redirect:/artists";
    }

    @RequestMapping("/artist/{id}/songs")
    public String getArtistSongs(Model model, @PathVariable("id") Long id){
        Optional<Artist> artist = artistRepository.findById(id);

        if(artist.isPresent()){
            model.addAttribute("songs", songRepository.getAllByArtistsIsContaining(artist.get()));
            model.addAttribute("filer", "artist: " + artist.get().getFirstName() + " " + artist.get().getLastName());
        }
        else{
            model.addAttribute("song", new ArrayList<>());
            model.addAttribute("filter", "artist for this id dosen't exist");
        }

        return "song/list";
    }

    @GetMapping("/artist/new")
    public String newArtist(Model model){
        model.addAttribute("artist", new ArtistCommand());
        return "artist/addedit";
    }

    @GetMapping
    @RequestMapping("artist/new")
    public String newSong(Model model){
        model.addAttribute("artist", new ArtistCommand());
        return "artist/addedit";
    }


    @PostMapping("artist")
    public String saveOrUpdate(@ModelAttribute ArtistCommand command){
        Optional<Artist> artistOptional = artistRepository.getFirstByFirstNameAndLastName(command.getFirstName(), command.getLastName());
        if (!artistOptional.isPresent()){
            Artist detachedArtist = artistCommandToArtist.convert(command);
            Artist savedArtist = artistRepository.save(detachedArtist);
            return "redirect:/artist" + savedArtist.getId() + "/show";
        }
        else{
            System.out.println("Sorry, there's such artist in db");
            return "redirect:/artist" + artistOptional.get().getId() + "/show";
        }
    }
}
