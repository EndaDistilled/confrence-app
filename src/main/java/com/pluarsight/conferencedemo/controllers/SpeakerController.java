package com.pluarsight.conferencedemo.controllers;

import com.pluarsight.conferencedemo.models.Session;
import com.pluarsight.conferencedemo.models.Speaker;
import com.pluarsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {
    @Autowired
    private SpeakerRepository speakerRepository;
    //Read
    @GetMapping
    public List<Speaker>list(){
        return speakerRepository.findAll();
    }
    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepository.getOne(id);
    }

    //Create
    @PostMapping
    public  Speaker create(@RequestBody final Speaker speaker){
        return  speakerRepository.saveAndFlush(speaker);
    }
    //Delete
    @RequestMapping(value = "{id}", method =  RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

    //Update
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        //because this is a PUT, we expect all attributes to be passed in. A Patch
        //TODO: ADD VALIDATION THAT ALL attributes are passed in , otherwise return a 400 bad payload
        Speaker existingSession = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker,existingSession, "speaker_id");
        return speakerRepository.saveAndFlush(existingSession);
    }
}
