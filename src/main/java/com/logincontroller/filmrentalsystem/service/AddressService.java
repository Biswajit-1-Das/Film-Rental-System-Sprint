package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Integer id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
    }

    public List<Address> getAddressesByCity(Integer cityId) {
        return addressRepository.findByCityCityId(cityId);
    }

    public List<Address> searchByDistrict(String district) {
        return addressRepository.findByDistrictContainingIgnoreCase(district);
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }
}