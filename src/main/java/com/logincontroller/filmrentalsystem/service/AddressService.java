package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.model.City;
import com.logincontroller.filmrentalsystem.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressrepository;
    public Address saveAddress(Address address)
    {
        return address;
    }
    public List<Address> getAllAddress()
    {
        return addressrepository.findAll();
    }
    public Address findAddressByPostalCode(String postalCode) {
        return addressrepository.findByPostalCode(postalCode)
                .orElseThrow(() -> new RuntimeException("Address Not Found"));
    }
    public Address findByDistrict(String district)
    {
        return addressrepository.findByDistrict(district)
                .orElseThrow(() -> new RuntimeException("Address Not Found"));
    }
    public Address findAddressByID(int address_id)
    {
        return addressrepository.findById(address_id).orElseThrow(()->new RuntimeException("AddressNotFound"));
    }
    public Address findByPhone(String Phone)
    {
        return addressrepository.findByPhone(Phone)
                .orElseThrow(() -> new RuntimeException("Address Not found"));
    }
    public Address findByCity(City city)
    {
        return addressrepository.findByCity(city)
                .orElseThrow(() -> new RuntimeException("Address Not found"));
    }
}
