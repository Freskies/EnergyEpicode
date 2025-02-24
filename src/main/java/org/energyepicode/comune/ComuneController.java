package org.energyepicode.comune;


import lombok.RequiredArgsConstructor;
import org.energyepicode.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/comune")
@RequiredArgsConstructor
public class ComuneController {
	private final ComuneService comuneService;

	@GetMapping
	public List<Comune> getAllComuni () {
		return comuneService.findAll();
	}

	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public GeneralResponse createComune (@RequestBody Comune comune) {
		comuneService.save(comune);
		return new GeneralResponse(comune.getId());
	}

}