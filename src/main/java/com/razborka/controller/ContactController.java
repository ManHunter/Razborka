package com.razborka.controller;

import com.razborka.model.Address;
import com.razborka.model.Phone;
import com.razborka.service.AddressService;
import com.razborka.service.PhoneService;
import com.razborka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Admin on 21.04.2015.
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(value = "/save_address", method = RequestMethod.POST)
    public @ResponseBody String saveAddress(@RequestParam("country") String country,
                                            @RequestParam("city") String city,
                                            @RequestParam("address") String address) {
        Address a = new Address(userService.getCurrentUser(),
                country,
                city,
                address);
        addressService.saveAddress(a);

        return "OK";
    }

    @RequestMapping(value = "/save_phone", method = RequestMethod.POST)
    public @ResponseBody String savePhone(@RequestParam("phone") String phone) {
        Phone p = new Phone(userService.getCurrentUser(),
                            phone);
        phoneService.savePhone(p);

        return "OK";
    }
}
