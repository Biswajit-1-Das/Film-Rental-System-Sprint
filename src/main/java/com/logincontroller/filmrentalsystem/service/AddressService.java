package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.AddressDTO;
import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private AddressDTO toDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setAddressId(address.getAddressId());
        dto.setAddress(address.getAddress());
        dto.setAddress2(address.getAddress2());
        dto.setDistrict(address.getDistrict());
        dto.setPostalCode(address.getPostalCode());
        dto.setPhone(address.getPhone());
        dto.setLocation(address.getLocation());
        dto.setLastUpdate(address.getLastUpdate());
        if (address.getCity() != null) {
            dto.setCityId(address.getCity().getCityId());
            dto.setCityName(address.getCity().getCity());
            if (address.getCity().getCountry() != null) {
                dto.setCountryId(address.getCity().getCountry().getCountryId());
                dto.setCountryName(address.getCity().getCountry().getCountry());
            }
        }
        return dto;
    }

    public Address getEntityById(Short id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
    }

    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AddressDTO getAddressById(Short id) {
        return toDTO(getEntityById(id));
    }

    public List<AddressDTO> getAddressesByCity(Short cityId) {
        return addressRepository.findByCityCityId(cityId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<AddressDTO> searchByDistrict(String district) {
        return addressRepository.findByDistrictContainingIgnoreCase(district)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AddressDTO saveAddress(Address address) {
        return toDTO(addressRepository.save(address));
    }

    public void deleteAddress(Short id) {
        addressRepository.deleteById(id);
    }
}
