package com.razborka.controller;

import com.razborka.model.*;
import com.razborka.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
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
    private PartTypeService partTypeService;

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

    @Autowired
    private ModelService modelService;

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


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    String deletePart(@PathVariable("id") int id, HttpServletRequest request) {
        int car_id = partService.getPartById(id).getCar().getId();

        List<Photo> photos = photoService.getAllPhotoByPartId(id);
        for (Photo photo : photos) {
            try {
                File file = new File(request.getServletContext().getRealPath("/") +
                                    "resources\\image\\part\\" +
                                    photo.getPicture());
                file.delete();
                photoService.deletePhoto(photo.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        partService.deletePart(id);
        return Integer.toString(car_id);
    }

    @RequestMapping(value = "/car/add", method = RequestMethod.POST)
    public String addCarAndPart(@ModelAttribute Part part,
                                @RequestParam("car_image_file") MultipartFile car_image_file,
                                @RequestParam("part_image_files[]") List<MultipartFile> part_image_files,
                                HttpServletRequest request) {
        User user = userService.getCurrentUser();
        Car car = part.getCar();
        car.setUser(user);

        if(!car_image_file.isEmpty()) {
            carService.saveCar(car,
                               car_image_file,
                               request.getServletContext().getRealPath("/"));
        }

        part.getCar().setUser(user);
        partService.savePart(part);
        Photo photo = new Photo();
        try {
            for (MultipartFile file : part_image_files) {
                if (!file.isEmpty()) {
                    String filename = generateFilename(file.getOriginalFilename());
                    File f = new File(request.getServletContext().getRealPath("/") +
                            "resources\\image\\part\\" +
                            filename);
                    FileUtils.writeByteArrayToFile(f, file.getBytes());
                    photo.setPicture(filename);
                    photo.setPart(part);
                    photoService.savePhoto(photo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error save photo: " + e.getMessage());
        }

        return "redirect:/profile/cars";
    }

    @RequestMapping(value = "/add/{car_id}", method = RequestMethod.POST)
    public String addPartToCar(@ModelAttribute Part part,
                        @RequestParam("part_image_files[]") List<MultipartFile> part_image_files,
                        @PathVariable("car_id") int car_id,
                        HttpServletRequest request) {
        Car car = carService.getCarById(car_id);
        part.setCar(car);
        partService.savePart(part);

        Photo photo = new Photo();
        try {
            for (MultipartFile file : part_image_files) {
                if (!file.isEmpty()) {
                    String filename = generateFilename(file.getOriginalFilename());
                    File f = new File(request.getServletContext().getRealPath("/") +
                                    "resources\\image\\part\\" +
                                    filename);
                    FileUtils.writeByteArrayToFile(f, file.getBytes());
                    photo.setPicture(filename);
                    photo.setPart(part);
                    photoService.savePhoto(photo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error save photo: " + e.getMessage());
        }

        return "redirect:/profile/parts/" + car_id;
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

//    @RequestMapping(value = "/part/edit", method = RequestMethod.GET)
//    public String editPart(@RequestParam("id") int id, ModelMap model) {
//        Part part = partService.getPartById(id);
//        model.addAttribute("brands", brandService.getAllBrand());
//        model.addAttribute("bodies", bodyTypeService.getAllBody());
//        model.addAttribute("kpps", kppService.getAllKpp());
//        model.addAttribute("fuels", fuelService.getAllFuel());
//        model.addAttribute("partGroups", partGroupService.getAllPartGroup());
//        model.addAttribute("part", part);
//
//        return "part_edit";
//    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editPart(@ModelAttribute Part part,
                    @RequestParam("part_image_files[]") List<MultipartFile> part_image_files,
                    @PathVariable("id") int id,
                    HttpServletRequest request) {
        Car car = partService.getPartById(id).getCar();
        part.setCar(car);
        partService.updatePart(part);

        Photo photo = new Photo();
        try {
            for (MultipartFile file : part_image_files) {
                if (!file.isEmpty()) {
                    String filename = generateFilename(file.getOriginalFilename());
                    File f = new File(request.getServletContext().getRealPath("/") +
                            "resources\\image\\part\\" +
                            filename);
                    FileUtils.writeByteArrayToFile(f, file.getBytes());
                    photo.setPicture(filename);
                    photo.setPart(part);
                    photoService.savePhoto(photo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error save photo: " + e.getMessage());
        }

        return "redirect:/profile/parts/" + car.getId();
    }

    public String generateFilename(String originalFilename) {
        Long nameRandom = Calendar.getInstance().getTimeInMillis();
        originalFilename = nameRandom + "_" + originalFilename;
        return originalFilename;
    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Part getPart(@PathVariable("id") int id) {
        return partService.getPartById(id);
    }
}
