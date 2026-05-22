package com.uth.fms.customer.services.impl;

import com.uth.fms.customer.entity.Customer;
import com.uth.fms.customer.repositories.CustomerRepository;
import com.uth.fms.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAllByDeleteFlagFalse();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findByIdAndDeleteFlagFalse(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setDeleteFlag(false);
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        
        customer.setFullName(customerDetails.getFullName());
        customer.setPhone(customerDetails.getPhone());
        customer.setEmail(customerDetails.getEmail());
        customer.setAddress(customerDetails.getAddress());
        customer.setTaxCode(customerDetails.getTaxCode());
        customer.setNote(customerDetails.getNote());
        customer.setAssignedSale(customerDetails.getAssignedSale());
        
        return customerRepository.save(customer);
    }

    @Override
    public void softDeleteCustomer(Long id) {
        Customer customer = customerRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customer.setDeleteFlag(true);
        customerRepository.save(customer);
    }
}
