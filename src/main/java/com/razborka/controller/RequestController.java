package com.razborka.controller;

import com.razborka.model.Request;
import com.razborka.model.User;
import com.razborka.service.RequestService;
import com.razborka.service.UserService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Admin on 08.05.2015.
 */
@Controller
@RequestMapping(value = "/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String home(ModelMap model) {
        model.addAttribute("requests", requestService.getPublicRequests());
        return "requests";
    }

    @RequestMapping(value = "/send_form", method = RequestMethod.POST)
    public String saveRequestForm(@RequestParam(value = "user", required = false) Integer user_id,
                              @RequestParam("brand") String brand,
                              @RequestParam("model") String model,
                              @RequestParam("year") String year,
                              @RequestParam("catalog") String catalogNumber,
                              @RequestParam("desc") String description) {
        User user;
        if(user_id == null)
            user = null;

        User user_from;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)) {
            user_from = userService.getCurrentUser();
        } else {
            return "redirect:/registration";
        }
        Request request = new Request();
        request.setUser_from(user_from);
        request.setBrand(brand);
        request.setModel(model);
        request.setYear(year);
        request.setCatalogNumber(catalogNumber);
        request.setDescription(description);

        requestService.saveRequest(request);
        return "redirect:/";
    }


    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveRequest(@RequestParam(value = "user", required = false) Integer user_id,
                       @RequestParam("brand") String brand,
                       @RequestParam("model") String model,
                       @RequestParam("year") String year,
                       @RequestParam("catalog") String catalogNumber,
                       @RequestParam("desc") String description) {
        User user;
        if(user_id == null)
            user = null;
        else
            user = userService.getUserById(user_id);
        User user_from = userService.getCurrentUser();

        Request request = new Request();
        request.setUser(user);
        request.setUser_from(user_from);
        request.setBrand(brand);
        request.setModel(model);
        request.setYear(year);
        request.setCatalogNumber(catalogNumber);
        request.setDescription(description);

        requestService.saveRequest(request);

        return "OK";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        requestService.deleteRequest(id);
        return "redirect:/profile/request";
    }
}
