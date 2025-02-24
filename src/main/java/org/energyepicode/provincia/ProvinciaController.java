package org.energyepicode.provincia;


import lombok.RequiredArgsConstructor;
import org.energyepicode.GeneralResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/province")
@RequiredArgsConstructor
public class ProvinciaController {
	private final ProvinciaService provinciaService;

	@GetMapping
	public List<Provincia> getAllProvince() {
		return provinciaService.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GeneralResponse createProvincia(@RequestBody Provincia provincia) {
		provinciaService.save(provincia);
		return new GeneralResponse(provincia.getId());
	}

}