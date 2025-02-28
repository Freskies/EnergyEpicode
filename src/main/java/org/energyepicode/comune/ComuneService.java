package org.energyepicode.comune;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.energyepicode.provincia.ProvinciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class ComuneService {
	private final ComuneRepository comuneRepository;


	public Comune save (Comune comune) {
		return comuneRepository.save(comune);
	}

	public Optional<Comune> findByNomeIgnoreCase (String nome) {
		return comuneRepository.findByNome(nome.substring(0,1).toUpperCase() + nome.substring(1).toLowerCase());
	}

	public List<Comune> findAll () {
		return comuneRepository.findAll();
	}
}
