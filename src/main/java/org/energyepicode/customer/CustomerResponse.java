package org.energyepicode.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.energyepicode.comune.Comune;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
	private Long id;

	private String companyName;
	private String vatNumber;
	private String email;


	private Date insertionDate;


	private Date lastContactDate;

	private Double annualTurnover;
	private String pec;
	private String phone;

	private String emailContact;
	private String nameContact;
	private String surnameContact;
	private String phoneContact;

	private String logo;

	private String via;
	private String civico;
	private String localita;
	private String cap;
	//@JsonIgnoreProperties ({"id", "provincia"})
	private Comune comune;

	// Tipo: PA, SAS, SPA, SRL

	private String tipo;
}
