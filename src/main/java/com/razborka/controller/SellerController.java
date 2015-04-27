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
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Admin on 11.04.2015.
 */
@Controller
@RequestMapping(value = "/profile/seller")
public class SellerController {

    private static final Logger log = Logger.getLogger(SellerController.class);

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


    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public String home(ModelMap model) {
        User user = userService.getCurrentUser();
        int user_id = user.getId();
        model.addAttribute("user", user);
        model.addAttribute("phones", phoneService.getPhoneByUserId(user_id));
        model.addAttribute("addresses", addressService.getAddressByUserId(user_id));
        model.addAttribute("partCount", partService.getAllUserPart(user_id).size());

        return "seller/index";
    }

    @RequestMapping(value = { "/user_info" }, method = RequestMethod.GET)
    public String profileSeller(ModelMap model) {
        model.addAttribute("user", userService.getCurrentUser());

        return "profile";
    }

    @RequestMapping(value = "/user_info/edit", method = RequestMethod.POST)
    public String editProfileSeller(@ModelAttribute User user) {
        userService.updateUser(user);

        return "profile";
    }

    @RequestMapping(value = "/parts", method = RequestMethod.GET)
    public String parts(ModelMap model) {
        User user = userService.getCurrentUser();
        //List<Part> parts = partService.getAllUserPart(user.getId());
        List<Car> cars = carService.getAllUserCars(user.getId());
        model.addAttribute("cars", cars);
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("bodies", bodyTypeService.getAllBody());
        model.addAttribute("kpps", kppService.getAllKpp());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("partGroups", partGroupService.getAllPartGroup());
        model.addAttribute("part", new Part());
        model.addAttribute("car", new Car());

        return "seller/parts";
    }



}
