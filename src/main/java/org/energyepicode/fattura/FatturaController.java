package org.energyepicode.fattura;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.energyepicode.GeneralResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping ("/fattura")
@RequiredArgsConstructor
public class FatturaController {
	private final FatturaService fatturaService;

	// GET /api/customers/invoices?filterByCustomerName=&filterByStatus=&filterByMinDate=&filterByMaxDate=&filterByMinAmount=&filterByMaxAmount=&page=&size=
	@GetMapping
	public Page<Fattura> getFatture (
		@RequestParam (value = "filterByCustomerName", required = false) String filterByCustomerName,
		@RequestParam (value = "filterByStatus", required = false) String filterByStatus,
		@RequestParam (value = "filterByMinDate", required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate filterByMinDate,
		@RequestParam (value = "filterByMaxDate", required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate filterByMaxDate,
		@RequestParam (value = "filterByMinAmount", required = false) Double filterByMinAmount,
		@RequestParam (value = "filterByMaxAmount", required = false) Double filterByMaxAmount,
		@RequestParam (value = "page", defaultValue = "0") int page,
		@RequestParam (value = "size", defaultValue = "10") int size) {

		Specification<Fattura> spec = Specification.where(null);
		if (filterByCustomerName != null) {
			spec = spec.and((root, query, cb) ->
				cb.like(cb.lower(root.join("customer").get("ragioneSociale")), "%" + filterByCustomerName.toLowerCase() + "%"));
		}
		if (filterByStatus != null) {
			spec = spec.and((root, query, cb) -> cb.equal(root.get("stato"), filterByStatus));
		}
		if (filterByMinDate != null) {
			spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("data"), Date.valueOf(filterByMinDate)));
		}
		if (filterByMaxDate != null) {
			spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("data"), Date.valueOf(filterByMaxDate)));
		}
		if (filterByMinAmount != null) {
			spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("importo"), filterByMinAmount));
		}
		if (filterByMaxAmount != null) {
			spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("importo"), filterByMaxAmount));
		}

		Pageable pageable = PageRequest.of(page, size);
		return fatturaService.findAll(spec, pageable);
	}

	// GET /api/customers/invoices/{id}
	@GetMapping ("/{id}")
	public Fattura getFattura (@PathVariable Long id) {
		return fatturaService.findById(id).orElseThrow(() -> new EntityNotFoundException("Fattura con id " + id + " non trovata"));
	}

	// POST /api/customers/invoices
	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public GeneralResponse createFattura (@RequestBody FatturaRequest fattura) {
		Fattura fatturaToSave = new Fattura();
		BeanUtils.copyProperties(fattura, fatturaToSave);
		Fattura saved = fatturaService.save(fatturaToSave);
		return new GeneralResponse(saved.getId());
	}

	// PATCH /api/customers/invoices/{id}
	@PatchMapping ("/{id}")
	public Fattura updateFattura (@PathVariable Long id, @RequestBody FatturaRequest fatturaDetails) {
		Fattura updated = fatturaService.findById(id).orElseThrow(() -> new EntityNotFoundException("Fattura con id " + id + " non trovata"));
		BeanUtils.copyProperties(fatturaDetails, updated);
		return updated;
	}

	// DELETE /api/customers/invoices/{id}
	@DeleteMapping ("/{id}")
	public ResponseEntity<Void> deleteFattura (@PathVariable Long id) {
		fatturaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}