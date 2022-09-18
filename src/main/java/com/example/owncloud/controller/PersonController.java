package com.example.owncloud.controller;

import com.example.owncloud.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Value("${server.port}")
    Integer port;
    @Value("${person.userName}")
    String userName;

    @Autowired
    public Person person;

    @RequestMapping("/readPro")
    public String readPro() {

        return "port: " + port + ",Person: " + userName;
    }

    @RequestMapping("/getPerson")
    public String readPerson() {

        return person.getUserName()+"  ,  "+person.getPassword();
    }
}
