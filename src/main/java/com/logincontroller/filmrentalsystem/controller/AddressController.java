package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.model.City;
import com.logincontroller.filmrentalsystem.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.*;
@RestController
@RequestMapping("/api/address")
public class AddressController {
    final private AddressService addressService;
    public AddressController(AddressService addressService)
    {
        this.addressService=addressService;
    }
    @GetMapping("/all")
    public List<Address> getAllAddresses()
    {
        return addressService.getAllAddress();
    }
    @GetMapping("/postal/{postalCode}")
    public Address findAddressByPostalCode(@PathVariable String postalCode)
    {
        return addressService.findAddressByPostalCode(postalCode);
    }
    @GetMapping("/district/{district}")
    public Address findByDistrict(@PathVariable String district)
    {
        return addressService.findByDistrict(district);
    }
    @GetMapping("/address_id/{address_id}")
    public Address findAddressByID(@PathVariable int address_id)
    {
        return addressService.findAddressByID(address_id);
    }
    @GetMapping("/Phone/{Phone}")
    public Address findByPhone(String phone)
    {
        return addressService.findByPhone(phone);
    }
    @GetMapping("/city/{city}")
    public Address findByCity(City city)
    {
        return addressService.findByCity(city);
    }
}

