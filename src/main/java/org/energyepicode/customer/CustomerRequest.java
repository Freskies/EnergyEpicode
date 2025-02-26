package org.energyepicode.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.energyepicode.fattura.Fattura;
import org.energyepicode.indirizzo.Indirizzo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

	private String companyName;
	private String vatNumber;
	@Email
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

	private Indirizzo indirizzoLegale;

	private Indirizzo indirizzoOperativo;

	// Tipo: PA, SAS, SPA, SRL

	private String tipo;

}