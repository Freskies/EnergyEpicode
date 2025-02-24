package org.energyepicode.provincia;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
	Optional<Provincia> findBySigla(String sigla);
	Optional<Provincia> findByNome(String nome);
}
