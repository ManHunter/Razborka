package com.razborka.controller;

import com.razborka.model.*;
import com.razborka.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    BrandService brandService;

    @Autowired
    ModelService modelService;

    @Autowired
    BodyTypeService bodyTypeService;

    @Autowired
    KppService kppService;

    @Autowired
    FuelService fuelService;

    @Autowired
    PartGroupService partGroupService;

    @Autowired
    PartTypeService partTypeService;

    @Autowired
    RepairTypeService repairTypeService;

    @RequestMapping(value = { "/", "" })
    public String home(ModelMap model) {

        return "admin/index";
    }

    @RequestMapping(value = "/category/brand", method = RequestMethod.GET)
    public String brandCategory(ModelMap model) {

        model.addAttribute("brand", new Brand());
        model.addAttribute("brands", brandService.getAllBrand());

        return "admin/brands";
    }

    /*======================================
    ОПЕРАЦИИ С МАРКАМИ АВТО
     ======================================*/
    @RequestMapping(value = "/category/brand", method = RequestMethod.POST)
    public String brandCategoryAdd(@ModelAttribute Brand brand, ModelMap model) {
        brandService.saveBrand(brand);

        return "redirect:/admin/category/brand";
    }
//
//    @RequestMapping(value = "/category/brand/edit", method = RequestMethod.GET)
//    public String edit

    @RequestMapping(value = "/category/brand/delete", method = RequestMethod.GET)
    public String deleteBrand(int id) {
        brandService.deleteBrand(id);

        return "redirect:/admin/category/brand";
    }

    /*======================================
    ОПЕРАЦИИ С МОДЕЛЯМИ АВТО
     ======================================*/
    @RequestMapping(value = "/category/model", method = RequestMethod.GET)
    public String modelCategory(ModelMap model) {
        model.addAttribute("model", new Model());
        model.addAttribute("models", modelService.getAllModel());
        model.addAttribute("brands", brandService.getAllBrand());

        return "admin/models";
    }

    @RequestMapping(value = "/category/model", method = RequestMethod.POST)
    public String modelCategoryAdd(@ModelAttribute Model model) {
        modelService.saveModel(model);

        return "redirect:/admin/category/model";
    }

    @RequestMapping(value = "/category/model/delete", method = RequestMethod.GET)
    public String deleteModel(int id) {
        modelService.deleteModel(id);

        return "redirect:/admin/category/model";
    }

    /*======================================
    ОПЕРАЦИИ С ТИПАМИ КУЗОВОВ АВТО
     ======================================*/
    @RequestMapping(value = "/category/body", method = RequestMethod.GET)
    public String bodyCategory(ModelMap model) {
        model.addAttribute("body", new Body());
        model.addAttribute("bodies", bodyTypeService.getAllBody());

        return "admin/bodies";
    }

    @RequestMapping(value = "/category/body", method = RequestMethod.POST)
    public String bodyCategoryAdd(@ModelAttribute Body body) {
        bodyTypeService.saveBody(body);

        return "redirect:/admin/category/body";
    }

    @RequestMapping(value = "/category/body/delete", method = RequestMethod.GET)
    public String deleteBody(int id) {
        bodyTypeService.deleteBody(id);

        return "redirect:/admin/category/body";
    }

    /*======================================
    ОПЕРАЦИИ С КПП АВТО
     ======================================*/
    @RequestMapping(value = "/category/kpp", method = RequestMethod.GET)
    public String kppCategory(ModelMap model) {
        model.addAttribute("kpp", new Kpp());
        model.addAttribute("kpps", kppService.getAllKpp());

        return "admin/kpp";
    }

    @RequestMapping(value = "/category/kpp", method = RequestMethod.POST)
    public String kppCategoryAdd(@ModelAttribute Kpp kpp) {
        kppService.saveKpp(kpp);

        return "redirect:/admin/category/kpp";
    }

    @RequestMapping(value = "/category/kpp/delete", method = RequestMethod.GET)
    public String deleteKpp(int id) {
        kppService.deleteKpp(id);

        return "redirect:/admin/category/kpp";
    }

    /*======================================
    ОПЕРАЦИИ С ТИП ТОПЛИВА АВТО
     ======================================*/
    @RequestMapping(value = "/category/fuel", method = RequestMethod.GET)
    public String fuelCategory(ModelMap model) {
        model.addAttribute("fuel", new Fuel());
        model.addAttribute("fuels", fuelService.getAllFuel());

        return "admin/fuel";
    }

    @RequestMapping(value = "/category/fuel", method = RequestMethod.POST)
    public String fuelCategoryAdd(@ModelAttribute Fuel fuel) {
        fuelService.saveFuel(fuel);

        return "redirect:/admin/category/fuel";
    }

    @RequestMapping(value = "/category/fuel/delete", method = RequestMethod.GET)
    public String deleteFuel(int id) {
        fuelService.deleteFuel(id);

        return "redirect:/admin/category/fuel";
    }

    /*======================================
    ОПЕРАЦИИ С ГРУППА З/Ч АВТО
     ======================================*/
    @RequestMapping(value = "/category/part_group", method = RequestMethod.GET)
    public String groupCategory(ModelMap model) {
        model.addAttribute("partGroup", new PartGroup());
        model.addAttribute("partGroups", partGroupService.getAllPartGroup());

        return "admin/part_group";
    }

    @RequestMapping(value = "/category/part_group", method = RequestMethod.POST)
    public String groupCategoryAdd(@ModelAttribute PartGroup partGroup) {
        partGroupService.savePartGroup(partGroup);

        return "redirect:/admin/category/part_group";
    }

    @RequestMapping(value = "/category/part_group/delete", method = RequestMethod.GET)
    public String deleteGroup(int id) {
        partGroupService.deletePartGroup(id);

        return "redirect:/admin/category/part_group";
    }

    /*======================================
    ОПЕРАЦИИ С ТИП З/Ч АВТО
     ======================================*/
    @RequestMapping(value = "/category/part_type", method = RequestMethod.GET)
    public String typeCategory(ModelMap model) {
        model.addAttribute("partType", new PartType());
        model.addAttribute("partTypes", partTypeService.getAllPartType());
        model.addAttribute("partGroups", partGroupService.getAllPartGroup());

        return "admin/part_type";
    }

    @RequestMapping(value = "/category/part_type", method = RequestMethod.POST)
    public String typeCategoryAdd(@ModelAttribute PartType partType) {
        partTypeService.savePartType(partType);

        return "redirect:/admin/category/part_type";
    }

    @RequestMapping(value = "/category/part_type/delete", method = RequestMethod.GET)
    public String deleteType(int id) {
        partTypeService.deletePartType(id);

        return "redirect:/admin/category/part_type";
    }

    /*======================================
    ОПЕРАЦИИ С УСЛУГИ СТО АВТО
     ======================================*/
    @RequestMapping(value = "/category/repairType", method = RequestMethod.GET)
    public String repairTypeCategory(ModelMap model) {
        model.addAttribute("repairType", new RepairType());
        model.addAttribute("repairTypes", repairTypeService.getAllRepairType());

        return "admin/repairType";
    }

    @RequestMapping(value = "/category/repairType", method = RequestMethod.POST)
    public String repairTypeCategoryAdd(@ModelAttribute RepairType repairType) {
        repairTypeService.saveRepairType(repairType);

        return "redirect:/admin/category/repairType";
    }

    @RequestMapping(value = "/category/repairType/delete", method = RequestMethod.GET)
    public String deleteRepairType(int id) {
        repairTypeService.deleteRepairType(id);

        return "redirect:/admin/category/repairType";
    }
}
