package com.razborka.controller;

import com.razborka.model.*;
import com.razborka.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Admin on 15.04.2015.
 */
@Controller
@RequestMapping(value = "/car")
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
    public @ResponseBody
    Car editCar(@ModelAttribute Car car, @PathVariable("id") int car_id) {
        User user = carService.getCarById(car_id).getUser();
        car.setUser(user);
        carService.updateCar(car);
        return carService.getCarById(car_id);
    }
}
