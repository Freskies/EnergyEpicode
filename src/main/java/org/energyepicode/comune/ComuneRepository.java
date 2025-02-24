package org.energyepicode.comune;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComuneRepository extends JpaRepository<Comune, Long> {
	Optional<Comune> findByNome(String nome);
}
