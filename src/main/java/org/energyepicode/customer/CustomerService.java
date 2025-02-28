package org.energyepicode.customer;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class CustomerService {
	private final CustomerRepository customerRepository;

	public Customer save (Customer customer) {
		return customerRepository.save(customer);
	}

	public Optional<Customer> findById (Long id) {
		return customerRepository.findById(id);
	}

	public void delete (Long id) {
		customerRepository.deleteById(id);
	}

	public Page<CustomerResponse> findCustomers (Specification<Customer> spec, Pageable pageable) {
		Page<Customer> customers = customerRepository.findAll(spec, pageable);
		return customers.map(this::toCustomerResponse);

	}

	private CustomerResponse toCustomerResponse (Customer customer) {
		CustomerResponse customerResponse = new CustomerResponse();
		BeanUtils.copyProperties(customer, customerResponse);
		return customerResponse;
	}

}
