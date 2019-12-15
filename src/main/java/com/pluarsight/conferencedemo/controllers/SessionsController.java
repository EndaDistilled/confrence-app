package com.pluarsight.conferencedemo.controllers;

import com.pluarsight.conferencedemo.models.Session;
import com.pluarsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){

        return sessionRepository.getOne(id);
    }
    //Create
    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    //Delete
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        //Also need to check children records before deleting
        sessionRepository.deleteById(id);
    }

    //Update
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session){
        //because this is a PUT, we expect all attributes to be passed in. A Patch
        //TODO: ADD VALIDATION THAT ALL attributes are passed in , otherwise return a 400 bad payload
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session,existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }


}
