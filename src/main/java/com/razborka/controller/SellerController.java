package com.razborka.controller;

import com.razborka.model.*;
import com.razborka.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    private PartTypeService partTypeService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CarService carService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ModelService modelService;

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
        List<Part> parts = partService.getAllUserPart(user.getId());

        model.addAttribute("parts", parts);

        return "seller/parts";
    }

    @RequestMapping(value = "/part/add", method = RequestMethod.GET)
    public String addFormPart(ModelMap model) {
        model.addAttribute("part", new Part());
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("bodies", bodyTypeService.getAllBody());
        model.addAttribute("kpps", kppService.getAllKpp());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("partGroups", partGroupService.getAllPartGroup());

        return "seller/part_add";
    }

    @RequestMapping(value = "/part/add", method = RequestMethod.POST)
    public String savePart(Part part) {
        User user = userService.getCurrentUser();
        part.setUser(user);
        partService.savePart(part);
        return "redirect:/profile/seller/parts";
    }

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

    @RequestMapping(value = "/part/edit", method = RequestMethod.POST)
    public String editPart(@ModelAttribute Part part) {
        log.info(part);
        System.out.println("car.id" + part.getCar().getId());
        partService.updatePart(part);

        return "redirect:/";
    }

}
