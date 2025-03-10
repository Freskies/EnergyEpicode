package org.energyepicode.indirizzo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class IndirizzoService {
	private final IndirizzoRepository indirizzoRepository;

	public Indirizzo save(Indirizzo indirizzo) {
		return indirizzoRepository.save(indirizzo);
	}

	public Optional<Indirizzo> findById(Long id) {
		return indirizzoRepository.findById(id);
	}
}
