package com.razborka.controller;

import com.razborka.model.Part;
import com.razborka.service.PartService;
import com.razborka.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Admin on 19.04.2015.
 */
@Controller
@RequestMapping(value = "/parts")
public class PartController {

    @Autowired
    private PartService partService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public String home(ModelMap model) {
        model.addAttribute("parts", partService.getAllPart());

        return "parts";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showPart(@RequestParam("id") int id, ModelMap model) {
        Part part = partService.getPartById(id);
        model.addAttribute("part", part);
        model.addAttribute("reviews", reviewService.getReviewByUserId(
                part.getUser().getId()));

        return "part";
    }

}
