package com.razborka.controller;

import com.razborka.model.*;
import com.razborka.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by Admin on 15.04.2015.
 */
@Controller
@RequestMapping(value = "/cars")
public class CarController {

    @Autowired
    private PartTypeService partTypeService;

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
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private PartService partService;


    /**
     * Все автомобили пользователя (Разборки, СТО, частника)
     * @param model
     * @return
     */
    @RequestMapping(value = "/user_cars")
    public String userCars(ModelMap model) {
        User user = userService.getCurrentUser();
        model.addAttribute("cars", carService.getAllUserCars(user.getId()));
        return "cars/cars";
    }

    @RequestMapping(value = "/models", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Model> getModel(@RequestParam("brandId") int brandId) {
        System.out.print("asdasd " + brandId);
        List<Model> models = modelService.getModelByBrandId(brandId);
        System.out.print("asdasd " + models.get(0).getName());
        return models;
    }

    @RequestMapping(value = "/part_types", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<PartType> getPartTypes(@RequestParam("groupId") int groupId) {
        List<PartType> partTypes = partTypeService.getPartTypeByGroupId(groupId);
        return partTypes;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public @ResponseBody
    Car editCarForm(@PathVariable("id") int car_id) {
        Car car = carService.getCarById(car_id);
        //System.out.println("id car " + car.getId());
        return car;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editCar(@ModelAttribute Car car,
                @RequestParam(value = "car_image_file", required = false)MultipartFile car_image_file,
                @PathVariable("id") int car_id,
                HttpServletRequest request) {
        User user = carService.getCarById(car_id).getUser();
        car.setUser(user);

        if(car_image_file != null) {
            if (!car_image_file.isEmpty()) {
                carService.updateCar(car,
                        car_image_file,
                        request.getServletContext().getRealPath("/"));
            } else {
                carService.updateCar(car);
            }
        } else {
            carService.updateCar(car);
        }
        return "redirect:/profile/cars";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteCar(@PathVariable("id") int car_id, HttpServletRequest request) {
        Car car = carService.getCarById(car_id);
        List<Part> parts = car.getParts();
        for (Part part : parts) {
            List<Photo> photos = photoService.getAllPhotoByPartId(part.getId());
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
            partService.deletePart(part.getId());
        }

        try {
            File file = new File(request.getServletContext().getRealPath("/") +
                    "resources\\image\\car\\" +
                    car.getPhoto());
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        carService.deleteCar(car_id);

        return "OK";
    }
}
