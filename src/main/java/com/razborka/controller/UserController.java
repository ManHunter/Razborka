package com.razborka.controller;

import com.razborka.Constants;
import com.razborka.model.*;
import com.razborka.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
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

    @Autowired
    private RepairTypeService repairTypeService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String home(ModelMap model) {
        User user = userService.getCurrentUser();
        int user_id = user.getId();
        model.addAttribute("user", user);
        model.addAttribute("phones", phoneService.getPhoneByUserId(user_id));
        model.addAttribute("addresses", addressService.getAddressByUserId(user_id));
        model.addAttribute("partCount", partService.getAllUserPart(user_id).size());
        model.addAttribute("news", newsService.getAllNews());

        return "user/index";
    }

    @RequestMapping(value = "/get/repair_types", method = RequestMethod.POST)
    public
    @ResponseBody
    List<RepairType> getServiceList() {
        User user = userService.getCurrentUser();
        return repairTypeService.getAllRepairTypeExceptUser(user.getId());
    }


    @RequestMapping(value = {"/user_info"}, method = RequestMethod.GET)
    public String profileSeller(ModelMap model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("repairTypes", repairTypeService.getAllRepairTypeExceptUser(user.getId()));
        model.addAttribute("myServices", serviceService.getAllUserServices(user.getId()));
        return "user/profile";
    }

    @RequestMapping(value = "/user_info/edit", method = RequestMethod.POST)
    public String editProfileSeller(@ModelAttribute User user,
                                    @RequestParam("razborka_image_files[]") List<MultipartFile> razborka_image_files,
                                    HttpServletRequest request) {
        int i = 0;
        for (MultipartFile file : razborka_image_files) {
            try {
                if (!file.isEmpty()) {
                    String filename = generateFilename(file.getOriginalFilename());
                    File f = new File(request.getServletContext().getRealPath("/") +
                            "resources\\image\\user\\" +
                            filename);
                    FileUtils.writeByteArrayToFile(f, file.getBytes());
                    switch (i) {
                        case 0:
                            user.setPhoto1(filename);
                            break;
                        case 1:
                            user.setPhoto2(filename);
                            break;
                        case 2:
                            user.setPhoto3(filename);
                            break;
                    }
                    i++;
                }
            } catch (IOException e) {
                System.out.println("Error save photo: " + e.getMessage());
            }
        }
        userService.updateUser(user);

        return "redirect:/profile/user_info";
    }

    @RequestMapping(value = "/change_password")
    public String changePassword(
            @RequestParam(value = "new_password", required = false) String newPassword,
            @RequestParam(value = "old_password", required = false) String oldPassword) {
        if (newPassword != null && oldPassword != null) {
            User user = userService.getCurrentUser();
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                userService.updateUser(user);
            }
        } else {

        }
        return "user/password";
    }

    /**
     * Все автомобили пользователя (Разборки, СТО, частника)
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/cars")
    public String userCars(ModelMap model) {
        User user = userService.getCurrentUser();
        model.addAttribute("cars", carService.getAllUserCars(user.getId()));
        return "user/cars";
    }

    @RequestMapping(value = "/parts", method = RequestMethod.GET)
    public String parts(ModelMap model,
                        @RequestParam(value = "brand", defaultValue = "0", required = false) int brand_id,
                        @RequestParam(value = "model", defaultValue = "0", required = false) int model_id,
                        @RequestParam(value = "year", defaultValue = "0", required = false) int year,
                        @RequestParam(value = "volume", defaultValue = "0", required = false) int volume,
                        @RequestParam(value = "fuel", defaultValue = "0", required = false) int fuel_id,
                        @RequestParam(value = "body", defaultValue = "0", required = false) int body_id,
                        @RequestParam(value = "part_group", defaultValue = "0", required = false) int part_group_id,
                        @RequestParam(value = "part_type", defaultValue = "0", required = false) int part_type_id,
                        @RequestParam(value = "page", defaultValue = "1", required = false) int page) {
        User user = userService.getCurrentUser();
        int user_id = user.getId();
        model.addAttribute("parts", partService.partFilter(user_id, brand_id, model_id, year, volume, fuel_id,
                body_id, part_group_id, part_type_id, "0", page - 1));
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        model.addAttribute("volumes", carService.getAllEngineVolume());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("bodys", bodyTypeService.getAllBody());
        model.addAttribute("groups", partGroupService.getAllPartGroup());
        model.addAttribute("types", partTypeService.getAllPartType());

        int partSize = partService.numberOfParts(user_id, brand_id, model_id, year, volume, fuel_id, body_id,
                part_group_id, part_type_id, "0");
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

        return "user/parts_car";
    }

    @RequestMapping(value = "/parts/{car_id}", method = RequestMethod.GET)
    public String carParts(ModelMap model,
                           @PathVariable("car_id") int car_id,
                           @RequestParam(value = "brand", defaultValue = "0", required = false) int brand_id,
                           @RequestParam(value = "model", defaultValue = "0", required = false) int model_id,
                           @RequestParam(value = "year", defaultValue = "0", required = false) int year,
                           @RequestParam(value = "volume", defaultValue = "0", required = false) int volume,
                           @RequestParam(value = "fuel", defaultValue = "0", required = false) int fuel_id,
                           @RequestParam(value = "body", defaultValue = "0", required = false) int body_id,
                           @RequestParam(value = "part_group", defaultValue = "0", required = false) int part_group_id,
                           @RequestParam(value = "part_type", defaultValue = "0", required = false) int part_type_id,
                           @RequestParam(value = "page", defaultValue = "1", required = false) int page) {
        User user = userService.getCurrentUser();
        int user_id = user.getId();
        model.addAttribute("parts", partService.getUserPartsByCar(user_id, car_id, page-1));
        model.addAttribute("car", carService.getCarById(car_id));
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        model.addAttribute("volumes", carService.getAllEngineVolume());
        model.addAttribute("fuels", fuelService.getAllFuel());
        model.addAttribute("bodys", bodyTypeService.getAllBody());
        model.addAttribute("groups", partGroupService.getAllPartGroup());
        model.addAttribute("types", partTypeService.getAllPartType());

        int partSize = partService.numberOfParts(user_id, car_id);
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

        return "user/parts_car";
    }

    @RequestMapping(value = "/part/add/", method = RequestMethod.GET)
    public String addPart(ModelMap model) {
        model.addAttribute("part", new Part());
        model.addAttribute("partGroups", partGroupService.getAllPartGroup());
        model.addAttribute("partTypes", partTypeService.getAllPartType());
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("models", modelService.getAllModel());
        model.addAttribute("bodies", bodyTypeService.getAllBody());
        model.addAttribute("kpps", kppService.getAllKpp());
        model.addAttribute("fuels", fuelService.getAllFuel());

        return "cars/car_add";
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

//    @RequestMapping(value = "/parts", method = RequestMethod.GET)
//    public String parts(ModelMap model) {
//        User user = userService.getCurrentUser();
//        List<Car> cars = carService.getAllUserCars(user.getId());
//        model.addAttribute("cars", cars);
//        model.addAttribute("brands", brandService.getAllBrand());
//        model.addAttribute("bodies", bodyTypeService.getAllBody());
//        model.addAttribute("kpps", kppService.getAllKpp());
//        model.addAttribute("fuels", fuelService.getAllFuel());
//        model.addAttribute("partGroups", partGroupService.getAllPartGroup());
//        model.addAttribute("part", new Part());
//        model.addAttribute("car", new Car());
//
//        return "parts";
//    }

    @RequestMapping(value = "/service/add", method = RequestMethod.POST)
    public
    @ResponseBody
    String addService(@RequestParam("service_id") int service_id) {
        Service service = new Service();
        service.setUser(userService.getCurrentUser());
        service.setType(repairTypeService.getRepairTypeById(service_id));
        serviceService.saveService(service);
        return "OK";
    }

    @RequestMapping(value = "/service/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteService(@RequestParam("service_id") int service_id) {
        serviceService.deleteService(service_id);
        return "OK";
    }

    public String generateFilename(String originalFilename) {
        Long nameRandom = Calendar.getInstance().getTimeInMillis();
        originalFilename = nameRandom + "_" + originalFilename;
        return originalFilename;
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public String reviews(ModelMap model) {
        User user = userService.getCurrentUser();
        model.addAttribute("reviews", reviewService.getReviewByUserId(user.getId()));
        return "user/reviews";
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public String comments(ModelMap model) {
        User user = userService.getCurrentUser();
        model.addAttribute("part_comments", commentService.getUserPartComments(user.getId()));
        model.addAttribute("car_comments", commentService.getUserCarComments(user.getId()));
        return "user/comments";
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public String requests(ModelMap model) {
        User user = userService.getCurrentUser();

        if(user.getRole().equals(Constants.SELLER_ROLE)) {
            model.addAttribute("requests", requestService.getRequestsByUser(user.getId()));
        } else {
            model.addAttribute("requests", requestService.getRequestsByUserFrom(user.getId()));
        }

        return "user/requests";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String messages(ModelMap model) {
        User user = userService.getCurrentUser();
        model.addAttribute("inboxMessages", messageService.getInboxMessage(user.getId()));
        model.addAttribute("outboxMessages", messageService.getOutboxMessage(user.getId()));
        return "user/messages";
    }

    @RequestMapping(value = "/new_message/{id}", method = RequestMethod.GET)
    public String newMessage(@PathVariable("id") int user_id, ModelMap model) {
        Message message = new Message();
        message.setUser(userService.getUserById(user_id));
        message.setUser_from(userService.getCurrentUser());
        model.addAttribute("message", message);
        return "user/message_add";
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public String showMessage(@PathVariable("id") int message_id, ModelMap model) {
        User user = userService.getCurrentUser();
        model.addAttribute("message", messageService.getMessage(message_id));
        model.addAttribute("user", user);
        return "user/message";
    }
}
