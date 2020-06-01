package com.januszcorporation.muslibryjkedition.controllers;

import com.januszcorporation.muslibryjkedition.commands.PublisherCommand;
import com.januszcorporation.muslibryjkedition.converters.PublisherCommandToPublisher;
import com.januszcorporation.muslibryjkedition.model.Publisher;
import com.januszcorporation.muslibryjkedition.repositories.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PublisherController {

    private PublisherRepository publisherRepository;
    private PublisherCommandToPublisher publisherCommandToPublisher;

    private PublisherController(PublisherRepository publisherRepository, PublisherCommandToPublisher publisherCommandToPublisher){
        this.publisherRepository = publisherRepository;
        this.publisherCommandToPublisher = publisherCommandToPublisher;
    }
    @RequestMapping("/publishers")
    public String getPublishers(Model model){
         model.addAttribute("publishers", publisherRepository.findAll());
         return "publisher/list";
    }

    @RequestMapping("/publisher/{id}/show")
    public String getPublisherDetails(Model model, @PathVariable("id") Long id){
        model.addAttribute("publisher", publisherRepository.findById(id).get());
        return "publisher/show";
    }

    @RequestMapping("/publisher/{id}/delete")
    public String deletePublisher(@PathVariable("id") Long id){
        publisherRepository.deleteById(id);
        return "redirect:/publishers";
    }

    @GetMapping
    @RequestMapping("/publisher/new")
    public String newSong(Model model){
        model.addAttribute("publisher", new PublisherCommand());
        return "publisher/addedit";
    }

    @PostMapping("publisher")
    public String saveOrUpdate(@ModelAttribute PublisherCommand command){
        Optional<Publisher> publisherOptional = publisherRepository.getPublisherByName(command.getName());
        if(!publisherOptional.isPresent()){
            Publisher detachedPublisher = publisherCommandToPublisher.convert(command);
            Publisher savedPublisher = publisherRepository.save(detachedPublisher);
            return "redirect:/publisher" + savedPublisher.getId() + "/show";
        }
        else{
            System.out.println("Sorry, there's such publisher in db");
            return "redirect:/publisher" + publisherOptional.get().getId() + "/show";
        }
    }
}
