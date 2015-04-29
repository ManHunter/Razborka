package com.razborka.controller;

import com.razborka.model.*;
import com.razborka.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Admin on 11.04.2015.
 */
@Controller
@RequestMapping(value = "/profile")
public class UserController {

    private static final Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PartService partService;

    @Autowired
    private PartGroupService partGroupService;

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
    private PhoneService phoneService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private PartTypeService partTypeService;

    @Autowired
    private PhotoService photoService;


    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public String home(ModelMap model) {
        User user = userService.getCurrentUser();
        int user_id = user.getId();
        model.addAttribute("user", user);
        model.addAttribute("phones", phoneService.getPhoneByUserId(user_id));
        model.addAttribute("addresses", addressService.getAddressByUserId(user_id));
        model.addAttribute("partCount", partService.getAllUserPart(user_id).size());

        return "user/index";
    }

    @RequestMapping(value = { "/user_info" }, method = RequestMethod.GET)
    public String profileSeller(ModelMap model) {
        model.addAttribute("user", userService.getCurrentUser());

        return "user/profile";
    }

    @RequestMapping(value = "/user_info/edit", method = RequestMethod.POST)
    public String editProfileSeller(@ModelAttribute User user) {
        userService.updateUser(user);

        return "user/profile";
    }

    /**
     * Все автомобили пользователя (Разборки, СТО, частника)
     * @param model
     * @return
     */
    @RequestMapping(value = "/cars")
    public String userCars(ModelMap model) {
        User user = userService.getCurrentUser();
        model.addAttribute("cars", carService.getAllUserCars(user.getId()));
        return "user/cars";
    }

    @RequestMapping(value = "/parts/{car_id}", method = RequestMethod.GET)
    public String carParts(@PathVariable("car_id") int car_id, ModelMap model) {
        model.addAttribute("parts", partService.getAllPartsCar(car_id));
        model.addAttribute("car", carService.getCarById(car_id));
        return "user/parts_car";
    }

    @RequestMapping(value = "/part/add/{id}", method = RequestMethod.GET)
    public String addPart(@PathVariable("id") int car_id, ModelMap model) {
        model.addAttribute("part", new Part());
        model.addAttribute("car_id", car_id);
        model.addAttribute("partGroups", partGroupService.getAllPartGroup());
        model.addAttribute("partTypes", partTypeService.getAllPartType());
        return "parts/part_add";
    }

    @RequestMapping(value = "/part/edit/{id}", method = RequestMethod.GET)
    public String editPart(@PathVariable("id") int part_id, ModelMap model) {
        model.addAttribute("part", partService.getPartById(part_id));
        model.addAttribute("partGroups", partGroupService.getAllPartGroup());
        model.addAttribute("partTypes", partTypeService.getAllPartType());
        return "parts/part_edit";
    }

    @RequestMapping(value = "/car/add", method = RequestMethod.GET)
    public String addCar(ModelMap model) {
        model.addAttribute("part", new Part());
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        model.addAttribute("bodies", bodyTypeService.getAllBody());
        model.addAttribute("kpps", kppService.getAllKpp());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("partGroups", partGroupService.getAllPartGroup());
        model.addAttribute("partTypes", partTypeService.getAllPartType());

        return "cars/car_add";
    }

    @RequestMapping(value = "/car/edit/{id}", method = RequestMethod.GET)
    public String editCar(@PathVariable("id") int car_id, ModelMap model) {
        model.addAttribute("car", carService.getCarById(car_id));
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        model.addAttribute("bodies", bodyTypeService.getAllBody());
        model.addAttribute("kpps", kppService.getAllKpp());
        model.addAttribute("fuels", fuelService.getAllFuel());
        return "cars/car_edit";
    }

    @RequestMapping(value = "/parts", method = RequestMethod.GET)
    public String parts(ModelMap model) {
        User user = userService.getCurrentUser();
        List<Car> cars = carService.getAllUserCars(user.getId());
        model.addAttribute("cars", cars);
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("bodies", bodyTypeService.getAllBody());
        model.addAttribute("kpps", kppService.getAllKpp());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("partGroups", partGroupService.getAllPartGroup());
        model.addAttribute("part", new Part());
        model.addAttribute("car", new Car());

        return "parts";
    }



}
