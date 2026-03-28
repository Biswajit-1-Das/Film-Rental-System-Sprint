package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.CustomerResponseDTO;
import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.model.Customer;
import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.repository.AddressRepository;
import com.logincontroller.filmrentalsystem.repository.CustomerRepository;
import com.logincontroller.filmrentalsystem.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final StoreRepository storeRepository;

    private CustomerResponseDTO toResponseDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setActive(customer.getActive() != null && customer.getActive() != 0);
        dto.setCreateDate(customer.getCreateDate());
        dto.setLastUpdate(customer.getLastUpdate());
        if (customer.getStore() != null) {
            dto.setStoreId(customer.getStore().getStoreId());
        }
        if (customer.getAddress() != null) {
            dto.setAddressId(customer.getAddress().getAddressId());
            dto.setAddressLine(customer.getAddress().getAddress());
            dto.setPostalCode(customer.getAddress().getPostalCode());
            dto.setPhone(customer.getAddress().getPhone());
            if (customer.getAddress().getCity() != null) {
                dto.setCity(customer.getAddress().getCity().getCity());
                if (customer.getAddress().getCity().getCountry() != null) {
                    dto.setCountry(
                            customer.getAddress().getCity().getCountry().getCountry()
                    );
                }
            }
        }
        return dto;
    }

    public Customer getEntityById(Short id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomerById(Short id) {
        return toResponseDTO(getEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> searchByLastName(String lastName) {
        return customerRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> searchByEmail(String email) {
        return customerRepository.findByEmailContainingIgnoreCase(email)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getActiveCustomers() {
        return customerRepository.findByActive((byte) 1)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getInactiveCustomers() {
        return customerRepository.findByActive((byte) 0)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getCustomersByStore(Byte storeId) {
        return customerRepository.findByStoreStoreId(storeId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomerByEmail(String email) {
        return customerRepository.findByEmailIgnoreCase(email)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstNameContainingIgnoreCase(firstName)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getCustomersByAddress(Short addressId) {
        return customerRepository.findByAddressAddressId(addressId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomerByIdAndAddressId(Short customerId, Short addressId) {
        return customerRepository.findByCustomerIdAndAddressAddressId(customerId, addressId)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException(
                        "Customer not found for id " + customerId + " and address " + addressId));
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getCustomersByCity(String city) {
        return customerRepository.findByAddress_City_City(city)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getCustomersByCountry(String country) {
        return customerRepository.findByAddress_City_Country_Country(country)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getCustomersByPhone(String phone) {
        return customerRepository.findByAddress_Phone(phone)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CustomerResponseDTO saveCustomer(Customer customer) {
        return toResponseDTO(customerRepository.save(customer));
    }

    @Transactional
    public CustomerResponseDTO createCustomer(Customer customer) {
        return toResponseDTO(customerRepository.save(customer));
    }

    @Transactional
    public CustomerResponseDTO updateCustomer(Short id, Customer updatedData) {
        Customer existing = getEntityById(id);
        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        existing.setEmail(updatedData.getEmail());
        if (updatedData.getStore() != null) {
            existing.setStore(updatedData.getStore());
        }
        if (updatedData.getAddress() != null) {
            existing.setAddress(updatedData.getAddress());
        }
        if (updatedData.getActive() != null) {
            existing.setActive(updatedData.getActive());
        }
        return toResponseDTO(customerRepository.save(existing));
    }

    @Transactional
    public CustomerResponseDTO updateFirstName(Short id, String firstName) {
        Customer c = getEntityById(id);
        c.setFirstName(firstName);
        return toResponseDTO(customerRepository.save(c));
    }

    @Transactional
    public CustomerResponseDTO updateLastName(Short id, String lastName) {
        Customer c = getEntityById(id);
        c.setLastName(lastName);
        return toResponseDTO(customerRepository.save(c));
    }

    @Transactional
    public CustomerResponseDTO updateEmail(Short id, String email) {
        Customer c = getEntityById(id);
        c.setEmail(email);
        return toResponseDTO(customerRepository.save(c));
    }

    @Transactional
    public CustomerResponseDTO updateStore(Short id, Byte storeId) {
        Customer c = getEntityById(id);
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found: " + storeId));
        c.setStore(store);
        return toResponseDTO(customerRepository.save(c));
    }

    @Transactional
    public CustomerResponseDTO updatePhone(Short id, String phone) {
        Customer c = getEntityById(id);
        Address a = c.getAddress();
        a.setPhone(phone);
        a.setLastUpdate(LocalDateTime.now());
        addressRepository.save(a);
        return toResponseDTO(getEntityById(id));
    }

    @Transactional
    public void deactivateCustomer(Short id) {
        Customer customer = getEntityById(id);
        customer.setActive((byte) 0);
        customerRepository.save(customer);
    }

    @Transactional
    public void activateCustomer(Short id) {
        Customer customer = getEntityById(id);
        customer.setActive((byte) 1);
        customerRepository.save(customer);
    }

    public void deleteCustomer(Short id) {
        customerRepository.deleteById(id);
    }
}
