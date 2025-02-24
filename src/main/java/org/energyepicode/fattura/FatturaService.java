package org.energyepicode.fattura;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
public class FatturaService {
	private final FatturaRepository fatturaRepository;


	public Fattura save(Fattura fattura) {
		return fatturaRepository.save(fattura);
	}

	public Optional<Fattura> findById(Long id) {
		return fatturaRepository.findById(id);
	}

	public void delete(Long id) {
		fatturaRepository.deleteById(id);
	}

	public Page<Fattura> findAll(Specification<Fattura> spec, Pageable pageable) {
		return fatturaRepository.findAll(spec, pageable);
	}

}
