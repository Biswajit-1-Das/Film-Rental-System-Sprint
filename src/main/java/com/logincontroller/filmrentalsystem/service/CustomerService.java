package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Customer;
import com.logincontroller.filmrentalsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // ── CREATE ────────────────────────────────────────────────
    @Transactional
    public Customer createCustomer(Customer customer) {
        customer.setActive(1);
        return customerRepository.save(customer);
    }

    // ── READ ──────────────────────────────────────────────────
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Customer not found with id: " + id));
    }

    public List<Customer> getCustomersByStore(int storeId) {
        return customerRepository.findByStoreStoreId(storeId);
    }

    public List<Customer> getActiveCustomers() {
        return customerRepository.findByActive(1);
    }

    public List<Customer> getInactiveCustomers() {
        return customerRepository.findByActive(0);
    }

    public List<Customer> getCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstNameIgnoreCase(firstName);
    }

    public List<Customer> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastNameIgnoreCase(lastName);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException(
                        "Customer not found with email: " + email));
    }

    public List<Customer> getCustomersByAddress(int addressId) {
        return customerRepository.findByAddressAddressId(addressId);
    }

    // ── UPDATE ────────────────────────────────────────────────
    @Transactional
    public Customer updateCustomer(int id, Customer updatedData) {
        Customer existing = getCustomerById(id);
        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        existing.setEmail(updatedData.getEmail());
        existing.setAddress(updatedData.getAddress());
        existing.setStore(updatedData.getStore());
        return customerRepository.save(existing);
    }

    // ── ACTIVATE / DEACTIVATE ─────────────────────────────────
    @Transactional
    public void activateCustomer(int id) {
        Customer customer = getCustomerById(id);
        customer.setActive(1);
        customerRepository.save(customer);
    }

    @Transactional
    public void deactivateCustomer(int id) {
        Customer customer = getCustomerById(id);
        customer.setActive(0);
        customerRepository.save(customer);
    }

    // ── DELETE ────────────────────────────────────────────────
    @Transactional
    public void deleteCustomer(int id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException(
                    "Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}