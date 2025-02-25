package org.energyepicode.provincia;

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
public class ProvinciaService {
	private final ProvinciaRepository provinciaRepository;

	public Provincia save(Provincia provincia) {
		return provinciaRepository.save(provincia);
	}

	public Optional<Provincia> findBySigla(String sigla) {
		return provinciaRepository.findBySigla(sigla);
	}

	public Optional<Provincia> findByNome(String nome) {
		return provinciaRepository.findByNome(nome);
	}

	public List<Provincia> findAll() {
		return provinciaRepository.findAll();
	}
}
