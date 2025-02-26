package org.energyepicode.fattura;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatturaRequest {
	private Date data;

	private Double importo;

	private String numero;

	//quello dinamico... da vedere ??
	private String stato;
}