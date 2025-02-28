package org.energyepicode.customer;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.energyepicode.GeneralResponse;
import org.energyepicode.comune.ComuneService;
import org.energyepicode.indirizzo.Indirizzo;
import org.energyepicode.indirizzo.IndirizzoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/customer")
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerService customerService;
	private final IndirizzoService indirizzoService;
	private final ComuneService comuneService;

	// GET /api/customers?orderByCompanyName=&filterByMinAnnualTurnover=&page=&size=...
	@GetMapping
	//da sistemare, con la customerResponse TODO,, problema
	public Page<Customer> getCustomers (
		@RequestParam (value = "orderByCompanyName", required = false) String orderByCompanyName,
		@RequestParam (value = "orderByAnnualTurnover", required = false) String orderByAnnualTurnover,
		@RequestParam (value = "orderByInsertionDate", required = false) String orderByInsertionDate,
		@RequestParam (value = "orderByLastContactDate", required = false) String orderByLastContactDate,
		@RequestParam (value = "orderByProvince", required = false) String orderByProvince,
		@RequestParam (value = "filterByMinAnnualTurnover", required = false) Double minAnnualTurnover,
		@RequestParam (value = "filterByMaxAnnualTurnover", required = false) Double maxAnnualTurnover,
		@RequestParam (value = "filterByMinInsertionDate", required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate minInsertionDate,
		@RequestParam (value = "filterByMaxInsertionDate", required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate maxInsertionDate,
		@RequestParam (value = "filterByMinLastContactDate", required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate minLastContactDate,
		@RequestParam (value = "filterByMaxLastContactDate", required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate maxLastContactDate,
		@RequestParam (value = "filterByCompanyName", required = false) String filterByCompanyName,
		@RequestParam (value = "page", defaultValue = "0") int page,
		@RequestParam (value = "size", defaultValue = "10") int size) {

		//costruisco la risposta in base ai parametri ricevuti dalla richiesta
		Specification<Customer> spec = Specification.where(null);
		if (minAnnualTurnover != null)
			spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("annualTurnover"), minAnnualTurnover));
		if (maxAnnualTurnover != null)
			spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("annualTurnover"), maxAnnualTurnover));
		if (minInsertionDate != null)
			spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("insertionDate"), Date.valueOf(minInsertionDate)));
		if (maxInsertionDate != null)
			spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("insertionDate"), Date.valueOf(maxInsertionDate)));
		if (minLastContactDate != null)
			spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("lastContactDate"), Date.valueOf(minLastContactDate)));
		if (maxLastContactDate != null)
			spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("lastContactDate"), Date.valueOf(maxLastContactDate)));
		if (filterByCompanyName != null)
			spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("companyName")), "%" + filterByCompanyName.toLowerCase() + "%"));


		List<Sort.Order> orders = new ArrayList<>();
		if (orderByCompanyName != null)
			orders.add(new Sort.Order(Sort.Direction.fromString(orderByCompanyName), "companyName"));
		if (orderByAnnualTurnover != null)
			orders.add(new Sort.Order(Sort.Direction.fromString(orderByAnnualTurnover), "annualTurnover"));
		if (orderByInsertionDate != null)
			orders.add(new Sort.Order(Sort.Direction.fromString(orderByInsertionDate), "insertionDate"));
		if (orderByLastContactDate != null)
			orders.add(new Sort.Order(Sort.Direction.fromString(orderByLastContactDate), "lastContactDate"));
		if (orderByProvince != null)

			orders.add(new Sort.Order(Sort.Direction.fromString(orderByProvince), "indirizzoLegale.comune.provincia.nome"));

		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Customer> result = customerService.findCustomers(spec, pageable);
		return result;
	}

	// GET /api/customers/{id}
	@GetMapping ("/{id}")
	public Customer getCustomer (@PathVariable Long id) {
		return customerService.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer con id " + id + " non trovato"));
	}

	// POST /api/customers
	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public GeneralResponse createCustomer (@RequestBody CustomerRequest customer) {
		Customer customerToSave = new Customer();
		Indirizzo indirizzoToSave = new Indirizzo();
		BeanUtils.copyProperties(customer, indirizzoToSave);
		if (comuneService.findByNomeIgnoreCase(customer.getComune().getNome()).isPresent()) {
			indirizzoToSave.setComune(comuneService.findByNomeIgnoreCase(customer.getComune().getNome()).get());
		}else{
			throw new EntityNotFoundException("Comune non trovato");
		}

		BeanUtils.copyProperties(customer, customerToSave);
		customerToSave.setIndirizzoLegale(indirizzoService.save(indirizzoToSave));
		customerService.save(customerToSave);
		return new GeneralResponse(customerToSave.getId());
	}

	// PATCH /api/customers/{id}
	@PatchMapping ("/{id}")
	public Customer updateCustomer (@PathVariable Long id, @RequestBody CustomerRequest customerDetails) {
		Customer updated = customerService.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer con id " + id + " non trovato"));
		BeanUtils.copyProperties(customerDetails, updated);
		return customerService.save(updated);
	}

	// DELETE /api/customers/{id}
	@DeleteMapping ("/{id}")
	public void deleteCustomer (@PathVariable Long id) {
		customerService.delete(id);
	}

}