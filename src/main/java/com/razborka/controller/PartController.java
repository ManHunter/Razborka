package com.razborka.controller;

import com.razborka.model.Car;
import com.razborka.model.Part;
import com.razborka.model.User;
import com.razborka.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Admin on 19.04.2015.
 */
@Controller
@RequestMapping(value = "/parts")
public class PartController {

    Logger log = Logger.getLogger(PartController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PartService partService;

    @Autowired
    private PartGroupService partGroupService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CarService carService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private BodyTypeService bodyTypeService;

    @Autowired
    private KppService kppService;

    @Autowired
    private FuelService fuelService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String home(ModelMap model) {
        model.addAttribute("parts", partService.getAllPart());

        return "parts";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showPart(@RequestParam("id") int id, ModelMap model) {
        Part part = partService.getPartById(id);
        model.addAttribute("part", part);
        model.addAttribute("reviews", reviewService.getReviewByUserId(
                part.getCar().getUser().getId()));

        return "part";
    }

    @RequestMapping(value = "/car/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Part> getPartsByCarId(@PathVariable("id") int id) {
        List<Part> parts = partService.getAllPartsCar(id);
        return parts;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Part getPart(@PathVariable("id") int id) {
        return partService.getPartById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    String deletePart(@PathVariable("id") int id) {
        int car_id = partService.getPartById(id).getCar().getId();
        partService.deletePart(id);
        return Integer.toString(car_id);
    }

//    @RequestMapping(value = "/car/{id}/add", method = RequestMethod.GET)
//    public String addFormPart(@PathVariable("id") int id, ModelMap model) {
//        Part part = new Part();
//        part.setCar(carService.getCarById(id));
//        model.addAttribute("part", part);
//        model.addAttribute("brands", brandService.getAllBrand());
//        model.addAttribute("bodies", bodyTypeService.getAllBody());
//        model.addAttribute("kpps", kppService.getAllKpp());
//        model.addAttribute("fuels", fuelService.getAllFuel());
//        model.addAttribute("partGroups", partGroupService.getAllPartGroup());
//
//        return "seller/part_add";
//    }

    @RequestMapping(value = "/car_part/add", method = RequestMethod.GET)
    public String addCarAndPart(ModelMap model) {
        model.addAttribute("part", new Part());
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("bodies", bodyTypeService.getAllBody());
        model.addAttribute("kpps", kppService.getAllKpp());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("partGroups", partGroupService.getAllPartGroup());

        return "seller/part_add";
    }

    @RequestMapping(value = "/car_part/add", method = RequestMethod.POST)
    public String addCarAndPart(@ModelAttribute Part part,
                              @RequestParam("files[]") List<MultipartFile> files,
                              HttpServletRequest request) {
        User user = userService.getCurrentUser();
        part.getCar().setUser(user);
        partService.savePart(part);
        photoService.savePhotos(files,
                part,
                request.getServletContext().getRealPath("/"));

        return "redirect:/profile/seller/parts";
    }

    @RequestMapping(value = "/part/add", method = RequestMethod.POST)
    public
    @ResponseBody
    String addPartToCar(@ModelAttribute Part part, @RequestParam("car_id") int car_id) {
        Car car = carService.getCarById(car_id);
        part.setCar(car);
        partService.savePart(part);

        return Integer.toString(part.getCar().getId());
    }

//    @RequestMapping(value = "/car/{id}/add", method = RequestMethod.POST)
//    public String addPartToCar(@PathVariable("id") int id, Part part) {
//        User user = userService.getCurrentUser();
//        Car car = carService.getCarById(id);
//        car.setUser(user);
//        part.setCar(car);
//        partService.savePart(part);
//        return "redirect:/profile/seller/parts";
//    }

    @RequestMapping(value = "/part/edit", method = RequestMethod.GET)
    public String editFormPart(@RequestParam("id") int id, ModelMap model) {
        Part part = partService.getPartById(id);
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("bodies", bodyTypeService.getAllBody());
        model.addAttribute("kpps", kppService.getAllKpp());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("partGroups", partGroupService.getAllPartGroup());
        model.addAttribute("part", part);

        return "seller/part_edit";
    }

    @RequestMapping(value = "/part/edit/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    String editPart(@ModelAttribute Part part, @PathVariable("id") int id) {
        Car car = partService.getPartById(id).getCar();
        part.setCar(car);
        partService.updatePart(part);

        return Integer.toString(part.getCar().getId());
    }
}
