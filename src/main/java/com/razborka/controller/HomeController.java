package com.razborka.controller;

import com.razborka.Constants;
import com.razborka.model.Review;
import com.razborka.model.User;
import com.razborka.service.*;
import com.razborka.service.Impl.UserDetailsServiceImpl;
import com.razborka.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Admin on 09.04.2015.
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private CarService carService;

    @Autowired
    private FuelService fuelService;

    @Autowired
    private BodyTypeService bodyTypeService;

    @Autowired
    private PartGroupService partGroupService;

    @Autowired
    private PartTypeService partTypeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private RepairTypeService repairTypeService;

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/")
    public String home(ModelMap model) {
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        model.addAttribute("volumes", carService.getAllEngineVolume());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("bodys", bodyTypeService.getAllBody());
        model.addAttribute("groups", partGroupService.getAllPartGroup());
        model.addAttribute("types", partTypeService.getAllPartType());
        model.addAttribute("cities", addressService.getAllCities());
        model.addAttribute("repairTypes", repairTypeService.getAllRepairType());
        model.addAttribute("news", newsService.getAllPublicNews());
        model.addAttribute("addresses", addressService.getAddressByUserRole(Constants.STO_ROLE));
        return "index";
    }

    @RequestMapping(value = "/registration")
    public String registration(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);

        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.saveUser(user);

        return "redirect:/";
    }

    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    public
    @ResponseBody
    String checkEmail(@RequestParam("email") String email) {
        if (userService.isAvailable(email))
            return "OK";
        else
            return "ERROR";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap model) {

        return "login";
    }

    @RequestMapping(value = "/razborki", method = RequestMethod.GET)
    public String razborka(ModelMap model,
                           @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
        int partSize = userService.getAllSeller(Constants.SELLER_ROLE).size();
        int numberPages = (int) Math.ceil(partSize * 1.0 / Constants.PART_PAGES);
        model.addAttribute("numberOfPages", numberPages);
        model.addAttribute("page", page);
        model.addAttribute("users", userService.getAllSeller(Constants.SELLER_ROLE, page - 1));
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        model.addAttribute("volumes", carService.getAllEngineVolume());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("bodys", bodyTypeService.getAllBody());
        model.addAttribute("groups", partGroupService.getAllPartGroup());
        model.addAttribute("types", partTypeService.getAllPartType());
        model.addAttribute("citys", addressService.getAllCities());
        model.addAttribute("addresses", addressService.getAddressByUserRole(Constants.SELLER_ROLE));
        return "razborki";
    }

    @RequestMapping(value = "/razborki/search", method = RequestMethod.GET)
    public String razborka(ModelMap model,
                           @RequestParam(value = "razborka", defaultValue = "0", required = false) Integer user_id,
                           @RequestParam(value = "brand", defaultValue = "0", required = true) int brand_id,
                           @RequestParam(value = "model", defaultValue = "0", required = false) int model_id,
                           @RequestParam(value = "year", defaultValue = "0", required = false) int year,
                           @RequestParam(value = "volume", defaultValue = "0", required = false) int volume,
                           @RequestParam(value = "fuel", defaultValue = "0", required = false) int fuel_id,
                           @RequestParam(value = "body", defaultValue = "0", required = false) int body_id,
                           @RequestParam(value = "part_group", defaultValue = "0", required = false) int part_group_id,
                           @RequestParam(value = "part_type", defaultValue = "0", required = false) int part_type_id,
                           @RequestParam(value = "city", defaultValue = "0", required = false) String city,
                           @RequestParam(value = "page", defaultValue = "1", required = false) int page) {


        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        model.addAttribute("volumes", carService.getAllEngineVolume());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("bodys", bodyTypeService.getAllBody());
        model.addAttribute("groups", partGroupService.getAllPartGroup());
        model.addAttribute("types", partTypeService.getAllPartType());
        model.addAttribute("citys", addressService.getAllCities());

        int partSize = userService.numberOfPage(user_id, brand_id, model_id, year, volume, fuel_id,
                body_id, part_group_id, part_type_id, city);
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

        model.addAttribute("users", userService.sellerFilter(user_id, brand_id, model_id, year, volume, fuel_id,
                body_id, part_group_id, part_type_id, city, page-1));

        return "razborki";
    }


    @RequestMapping(value = "/razborka/{id}", method = RequestMethod.GET)
    public String razborka(@PathVariable("id") int user_id, ModelMap model) {
        model.addAttribute("razborka", userService.getUserById(user_id));
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        return "razborka";
    }

    @RequestMapping(value = "/sto_all", method = RequestMethod.GET)
    public String sto(ModelMap model,
                      @RequestParam(value = "page", required = false) Integer page) {
        int partSize = userService.getAllSeller(Constants.STO_ROLE).size();
        int numberPages = (int) Math.ceil(partSize * 1.0 / Constants.PART_PAGES);
        if (page == null) page = 1;
        model.addAttribute("numberOfPages", numberPages);
        model.addAttribute("page", page);
        model.addAttribute("users", userService.getAllSeller(Constants.STO_ROLE, page - 1));
        model.addAttribute("cities", addressService.getAllCities());
        model.addAttribute("repairTypes", repairTypeService.getAllRepairType());
        model.addAttribute("addresses", addressService.getAddressByUserRole(Constants.STO_ROLE));
        return "sto_all";
    }

    @RequestMapping(value = "/sto_all/search", method = RequestMethod.GET)
    public String stoSearch(ModelMap model,
                      @RequestParam(value = "user_id", required = false) int user_id,
                      @RequestParam(value = "city", required = false) String city,
                      @RequestParam(value = "repair_type", required = false) int repairType,
                      @RequestParam(value = "page", required = false) Integer page) {
        int partSize = userService.numberOfPageSto(user_id, city, repairType);
        int numberPages = (int) Math.ceil(partSize * 1.0 / Constants.PART_PAGES);
        if (page == null) page = 1;
        model.addAttribute("numberOfPages", numberPages);
        model.addAttribute("page", page);
        model.addAttribute("users", userService.stoFilter(user_id, city, repairType, page-1));
        model.addAttribute("cities", addressService.getAllCities());
        model.addAttribute("repairTypes", repairTypeService.getAllRepairType());
        model.addAttribute("city_select", city);
        model.addAttribute("repairType_select", repairType);
        return "sto_all";
    }

    @RequestMapping(value = "/sto/{id}", method = RequestMethod.GET)
    public String sto(@PathVariable("id") int user_id, ModelMap model) {
        model.addAttribute("sto", userService.getUserById(user_id));
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        return "sto";
    }

//    @RequestMapping(value = "/review/add/{id}", method = RequestMethod.GET)
//    public String reviewAdd(@PathVariable("id") int user_id, ModelMap model) {
//        model.addAttribute("user", userService.getUserById(user_id));
//        model.addAttribute("review", new Review());
//        return "review_add";
//    }

    @RequestMapping(value = "/review/add", method = RequestMethod.POST)
    public
    @ResponseBody
    String reviewAdd(@RequestParam("user") int user_id,
                     @RequestParam("rating") int rating,
                     @RequestParam("description") String desc) {
        Review review = new Review();
        review.setRating(rating);
        review.setDescription(desc);
        review.setUser_from(userService.getCurrentUser());
        review.setUser(userService.getUserById(user_id));
        reviewService.saveReview(review);
        return "OK";
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        return "help";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contacts() {
        return "contacts";
    }
}
