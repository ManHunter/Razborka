package com.razborka.controller;

import com.razborka.model.Model;
import com.razborka.model.PartType;
import com.razborka.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
