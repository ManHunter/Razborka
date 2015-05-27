package com.razborka.controller;

import com.razborka.model.Message;
import com.razborka.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Admin on 09.05.2015.
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(@ModelAttribute("message") Message message) {
        System.out.println("====== public String send(@ModelAttribute(\"message\") Message message)");
        messageService.saveMessage(message);
        return "redirect:/profile/messages";
    }
}
