package com.razborka.controller;

import com.razborka.Constants;
import com.razborka.model.*;
import com.razborka.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private CommentService commentService;

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String home(ModelMap model,
                       @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
        int partSize = partService.getAllPart().size();
        int numberPages = (int) Math.ceil(partSize * 1.0 / Constants.PART_PAGES);
        //if (page == null) page = 1;
        model.addAttribute("parts", partService.getAllPart(page-1));
        model.addAttribute("numberOfPages", numberPages);
        model.addAttribute("page", page);
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        model.addAttribute("volumes", carService.getAllEngineVolume());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("bodys", bodyTypeService.getAllBody());
        model.addAttribute("groups", partGroupService.getAllPartGroup());
        model.addAttribute("types", partTypeService.getAllPartType());
        model.addAttribute("citys", addressService.getAllCities());
        return "parts";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(ModelMap model,
                         @RequestParam(value = "razborka", defaultValue = "0", required = false) int user_id,
                         @RequestParam(value = "brand", defaultValue = "0", required = false) int brand_id,
                         @RequestParam(value = "model", defaultValue = "0", required = false) int model_id,
                         @RequestParam(value = "year", defaultValue = "0", required = false) int year,
                         @RequestParam(value = "volume", defaultValue = "0", required = false) int volume,
                         @RequestParam(value = "fuel", defaultValue = "0", required = false) int fuel_id,
                         @RequestParam(value = "body", defaultValue = "0", required = false) int body_id,
                         @RequestParam(value = "part_group", defaultValue = "0", required = false) int part_group_id,
                         @RequestParam(value = "part_type", defaultValue = "0", required = false) int part_type_id,
                         @RequestParam(value = "city", defaultValue = "0", required = false) String city,
                         @RequestParam(value = "page", defaultValue = "1", required = false) int page) {
        model.addAttribute("parts", partService.partFilter(user_id, brand_id, model_id, year, volume, fuel_id, body_id, part_group_id, part_type_id, city, page-1));
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        model.addAttribute("volumes", carService.getAllEngineVolume());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("bodys", bodyTypeService.getAllBody());
        model.addAttribute("groups", partGroupService.getAllPartGroup());
        model.addAttribute("types", partTypeService.getAllPartType());
        model.addAttribute("citys", addressService.getAllCities());

        int partSize = partService.numberOfParts(user_id, brand_id, model_id, year, volume, fuel_id, body_id, part_group_id, part_type_id, city);
        int numberPages = (int) Math.ceil(partSize * 1.0 / Constants.PART_PAGES);
        model.addAttribute("numberOfPages", numberPages);
        model.addAttribute("page", page);

        model.addAttribute("brand_select", brand_id);
        model.addAttribute("model_select", model_id);
        model.addAttribute("year_select", year);
        model.addAttribute("volume_select", volume);
        model.addAttribute("fuel_select", fuel_id);
        model.addAttribute("body_select", body_id);
        model.addAttribute("group_select", part_group_id);
        model.addAttribute("type_select", part_type_id);
        model.addAttribute("city_select", city);

        return "parts";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showPart(@PathVariable("id") int id, ModelMap model) {
        Part part = partService.getPartById(id);
        int owner_part = part.getCar().getUser().getId();
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            Comment comment = new Comment();
            comment.setUser(userService.getCurrentUser());
            comment.setPart(part);
            model.addAttribute("comment", comment);
        }
        model.addAttribute("part", part);
        model.addAttribute("other_parts", partService.getUserPartsByCar(owner_part, part.getCar().getId(), 0));
        model.addAttribute("other_seller", partService.getPartsByOtherSeller(owner_part, part.getCar()));
        model.addAttribute("comments", commentService.getAllCommentsByPartId(id));
        model.addAttribute("reviews", reviewService.getReviewByUserId(owner_part));

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
                                @RequestParam(value = "car_image_file", required = false) MultipartFile car_image_file,
                                @RequestParam("part_image_files[]") List<MultipartFile> part_image_files,
                                HttpServletRequest request) {
        User user = userService.getCurrentUser();
        Car car = part.getCar();
        car.setUser(user);

        if(car_image_file != null) {
            if (!car_image_file.isEmpty()) {
                carService.saveCar(car,
                        car_image_file,
                        request.getServletContext().getRealPath("/"));
            }
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

    @RequestMapping(value = "/comment/add/{id}", method = RequestMethod.POST)
    public String addComment(@ModelAttribute Comment comment, @PathVariable("id") int id) {
        commentService.saveComment(comment);

        return "redirect:/parts/" + id;
    }
}
